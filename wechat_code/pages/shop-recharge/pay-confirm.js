const {
    update,session
} = require('../../api/index.js')
Page({

    /**
     * 页面的初始数据
     */
    data: {
        defaultSel: '',
        userInfo:'',
        list: [{
            name: "weixin",
            value: "1",
            label: "微信支付"
        }, {
            name: "zhifubao",
            value: "2",
            label: "支付宝支付"
        }, {
            name: "jianshe",
            value: "3",
            label: "建设银行"
        }, {
            name: "nongye",
            value: "4",
            label: "农业银行"
        }, {
            name: "zhongguo",
            value: "5",
            label: "中国银行"
        }, {
            name: "jiaotong",
            value: "6",
            label: "交通银行"
        }

        ]
    },

    /**
     * 生命周期函数--监听页面加载
     */
    async onLoad(options) {
        let table = wx.getStorageSync("nowTable");
        const userInfoRes = await session(table)
        if (userInfoRes.code == 0) {
            this.setData({
                userInfo: userInfoRes.data
            })
        }
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },
    payTap(e) {
        this.setData({
            defaultSel: e.detail.value
        })

    },
    onPageTap(e) {
        console.log('aaaaa', this.data.userInfo);
        let money = this.data.userInfo.money * 1
        let url = e.currentTarget.dataset.url
        if (this.data.defaultSel == "") {
            wx.showToast({
                title: '请选择支付类型',
                icon: 'none'
            })
            return;
        }
        let _that = this
        let table = wx.getStorageSync("paytable");
        let userName = wx.getStorageSync("nowTable");
        let obj = wx.getStorageSync("payObject");
        console.log('obj', obj);
        if (obj) {
            wx.showModal({
                title: '提示',
                content: '是否确认支付',
                success: async function (res) {
                    if (res.confirm) {
                        // 判断用户余额是否充足
                        if (money < Number(obj.price * obj.buynumber)) {
                            wx.showToast({
                                title: '余额不足，请前往充值',
                                icon: "none"
                            })
                            return
                        }
                        //减去用户余额
                        _that.setData({
                            'userInfo.money': (money - parseFloat(obj.price * obj.buynumber)).toFixed(2)
                        })
                        await update(userName, _that.data.userInfo)
                        obj.ispay = "已支付";
                        let payres = await update(table, obj)
                        if (payres.code == 0) {
                            wx.showToast({
                                title: '支付成功',
                                icon: 'none'
                            })
                            wx.navigateBack({
                                delta: 1,
                                complete: () => {
                                    // 触发事件通知，传递需要更新的数据
                                    const pages = getCurrentPages();
                                    if (pages.length >= 1) {
                                        const prePage = pages[pages.length - 1];
                                        prePage.onLoad(); //
                                    }
                                }
                            })
                        }

                    }
                }
            });
        } else {
            wx.navigateTo({
                url: "/pages/shop-recharge/recharge"
            })
        }


    },
    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    }
})