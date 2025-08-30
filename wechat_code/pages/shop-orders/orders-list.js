const {
    page,
    detail,
    update,
    info,
    session
} = require("../../api/index")
Page({
    data: {
        tabs: [],
        activeTab: 0,
        allList: [],
        baseURL: "",
        curList: "",
        parame: {},
        userInfo: ""
    },

    async onLoad(options) {
        const titles = ['全部',
            '已完成',
            '已发货',
            '已取消',
            '已支付',
            '已退款', ]
        const tabs = titles.map(item => ({
            title: item
        }))


        this.setData({
            tabs
        })
        const parame = {
            page: 1,
            limit: 10,
            sort: 'addtime',
            order: "desc"
        }
        const allListRes = await page('orders', parame)
        this.setData({
            allList: allListRes.data.list,
            baseURL: wx.getStorageSync('baseURL') + "/",
            curList: allListRes.data.list,
            parame
        })
        titles.forEach((item, index) => {
            if (options.name == item) {
                this.setData({
                    activeTab: index
                })
            }
        })
    },
    async onShow() {
        let table = wx.getStorageSync("nowTable");
        const res = await session(table)
        this.setData({
            userInfo: res.data
        })
        getApp().globalData.userInfo = res.data
    },
    onUnload() {
        wx.switchTab({
            url: '/pages/center/center'
        })
    },
    logisticsTap(e) {
        const item = e.currentTarget.dataset.item;
        wx.setStorageSync('logistics', item)
        wx.navigateTo({
            url: '/pages/shop-orders/logistics',
        })
    },
    // 去评价
    orderDiscuss(e) {
        const item = e.currentTarget.dataset.item;
        console.log(JSON.stringify(item))
                wx.navigateTo({
            url: `/pages/${item.tablename}/detail?id=${item.goodid}`,
        })
    },
    // 退款
    refundPriceClick(e) {
        const item = e.currentTarget.dataset.item;
        let table = wx.getStorageSync("nowTable");
        wx.showModal({
            title: '提示',
            content: '是否对该订单进行退款操作?',
            complete: async (res) => {
                if (res.confirm) {
                    const res = await info(item.tablename, item.goodid)
                    let data = res.data.data
                    let userInfo = getApp().globalData.userInfo
                    //如果商品存在库存，则加回去
                    // if (data.alllimittimes) {
                    //     data.alllimittimes = parseInt(data.alllimittimes) + parseInt(item.buynumber)
                    //     //更新商品库存
                    //     await update(item.tablename, data)
                    // }
                    if (item.type == 2) {
                        //如果是积分兑换，则把减去的积分加回去
                        userInfo['jf'] = parseInt(userInfo.jf) + parseInt(item.total)
                        const userRes = update(table, userInfo)
                        if (userRes.code == 0) {
                            item['status'] = '已退款'
                            //修改订单状态
                            const orderRes = await update('orders', item)
                            if (orderRes.code == 0) {
                                wx.showToast({
                                    title: '退款成功',
                                })
                                this.onLoad()
                                // this.statusChange()
                            }
                        }
                    } else {
                        //如果是购物或者团购模式，且商品存在积分，则把加上的积分减去
                        if (data?.jf) {
                            userInfo['jf'] = parseInt(userInfo.jf) - parseInt(item.total)
                        }
                        //把减去的余额加回去
                        userInfo['money'] = (parseFloat(userInfo.money) + parseFloat(item.total)).toFixed(2)
                        //更新用户信息
                        const userRes = await update(table, userInfo)
                        if (userRes.code == 0) {
                            item.status = '已退款'
                            await update('orders', item)
                            this.onLoad()
                        }
                    }
                }
            }
        })
    },
    cancelGroup(e) {
        const order = e.currentTarget.dataset.item
        wx.showModal({
            title: '提示',
            content: '是否取消拼单',
            complete: async (res) => {
                if (res.confirm) {
                    const res = await info(order.tablename, order.goodid)
                    let goodDetail = res.data;
                    goodDetail.curpeople = goodDetail.curpeople - 1;
                    const table = wx.getStorageSync('nowTable')
                    await update(order.tablename, goodDetail)
                    order.status = '已退款';
                    // 设置订单状态为取消
                    await update('orders', order)
                    // 重新加载数据
                    if (order.type == '1', order.type == '3') {
                        // 退回用户余额
                        const money = Number(this.data.userInfo.money) + Number(order.total);
                        // 退回用户余额
                        this.setData({
                            'userInfo.money': money
                        })
                    } else if (order.type == '2') {
                        // 退回用户积分
                        const jf = Number(this.data.userInfo.jf) + Number(order.total);
                        this.setData({
                            'userInfo.jf': jf
                        })
                    }
                    const resUser = await update(table, this.data.userInfo)
                    if (resUser.code == 0) {
                        this.onLoad()
                    }
                }
            }
        })

    },
    async onTabCLick(e) {
        const index = e.detail.index;
        let res;
        let parame = this.data.parame;
        if (index == 0) {
            delete parame.status;
        } else {
            parame["status"] = this.data.tabs[index].title;
        }
        res = await page('orders', parame)
        console.log("index", index, this.data.tabs[index].title, parame);
        this.setData({
            activeTab: index,
            curList: res.data.list
        });

    },
    //退货
    returnGood(e){
        const item = e.currentTarget.dataset.item;
        let table = wx.getStorageSync("nowTable");
        wx.showModal({
            title: '提示',
            content: '是否确认退货?',
            complete: async (res) => {
                if (res.confirm) {
                    item.status = '已退款';
                    // 设置订单状态为取消
                    await update('orders', item);
    // 加回库存数量
    if(item.tablename == `shangpin` && item.type!=3){
let res = await info(item.tablename, item.goodid);
        if(res.code==0){
            res.data.alllimittimes = Number(res.data.alllimittimes) + Number(item.buynumber);
            await   update(item.tablename,  res.data);
        }
    }

                    // 重新加载数据
                    if(item.type==1 || item.type==3) {
                        // 退回用户余额
                        this.data.userInfo.money= Number(this.data.userInfo.money) + Number(item.total);
                    } else if(order.type==2) {
                        // 退回用户积分
                        this.data.userInfo.jf = Number(        this.data.userInfo.jf) + Number(item.total);
                    }
                    update(table,this.data.userInfo);


                    // 重新加载数据
                    this.onLoad()
                }
            }
        })

    },

    //评价

    // 确认收货
    confrimReceipt(e) {
        const item = e.currentTarget.dataset.item;
        let table = wx.getStorageSync("nowTable");
        wx.showModal({
            title: '提示',
            content: '是否确认收货?',
            complete: async (res) => {
                if (res.confirm) {
                    item.status = '已完成';
                    // 设置订单状态为取消
                    let res = await update('orders', item);
                    // 重新加载数据
                    this.onLoad()
                }
            }
        })
    },
    onChange(e) {
        const index = e.detail.index
        this.setData({
            activeTab: index
        })
    }

})