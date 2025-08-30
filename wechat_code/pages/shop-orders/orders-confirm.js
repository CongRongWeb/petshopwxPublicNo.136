// pages/shop-order/submit.js
const {
    list,
    info,
    add,
    update,
    deleteData,
    session
} = require("../../api/index")
const utils = require("../../utils/index.js")
Page({

    /**
     * 页面的初始数据
     */
    data: {
address: "",
        orderGoods: "",
        types: "",
        userInfo: "",
        remark: "",
        baseURL:"",
        total:""
    },

    /**
     * 生命周期函数--监听页面加载
     */
    async onLoad(options) {
        let table = wx.getStorageSync("nowTable");
        const userInfoRes = await session(table)
                var address = wx.getStorageSync('address');
        if (!address) {
            const res = await list("address", data)
            res?.data?.list?.map((v, index) => {
                if (v.isdefault == '是') {
                    address = [v]
                }
            })
        }
        this.setData({
            address,
            userInfo: userInfoRes.data,
            baseURL: wx.getStorageSync('baseURL')+"/",
            total: getApp().globalData.total
        })
        const data = {
            userid: this.data.userInfo.id
        }
        const orderGoods = wx.getStorageSync('orderGoods');
        let total = 0
        orderGoods.map(v => {
            total += v.buynumber * v.price
        })
        this.setData({
            orderGoods,
            types: options?.type,
            seat :options?.seat,
            total: total.toFixed(2)
        })

    },
    addressTap() {
        wx.navigateTo({
            url: '/pages/address/list',
        })
    },
    //获取订单id
    createOrder() {
        let order = '';
        let now = new Date();
        order += now.getFullYear();
        order += now.getMonth() + 1;
        order += now.getDate();
        order += now.getHours();
        order += now.getMinutes();
        order += now.getSeconds();
        order += now.getMilliseconds();

        return order;
    },
    //正常支付
     payClick() {
                let table = wx.getStorageSync("nowTable");
                console.log('this.data.address.length',this.data.address.length);
    if (this.data.address.length == 0  ) {
             wx.showToast({
                 title: '请选择地址',
                 icon: "none"
             })
             return;
         }
                                const orderGoods = this.data.orderGoods

        orderGoods.forEach(async (item)=>{
            const res = await info(item.tablename, item.goodid)
            if (res){
                let orderId = this.createOrder()
                let infodata = res.data
                 const baseURL = wx.getStorageSync('baseURL') + "/"
                //添加订单
                let data = {
                    orderid: orderId,
                    tablename: item.tablename,
                    userid: this.data.userInfo.id,
                    goodid: item.goodid,
                    goodname: item.goodname,
                    picture: item.picture.replace(baseURL,""),
                    buynumber: item.buynumber,
                    discountprice:parseFloat(item.price),
                    price:parseFloat(item.price) ,
                    total:parseFloat(item.price * item.buynumber),
                    type: this.data.types?this.data.types:"1",
                    remark: this.data.remark,
//收货地址
                        address: this.data.types==1?'':this.data.address[0]?.address,
                        tel: this.data.types==1?'':this.data.address[0]?.phone,
                        consignee:this.data.types==1?'': this.data.address[0]?.name,


                    status: this.data.types == 3 ? '拼团中' : this.data.types == 1 ? '已支付' : '未支付'
                }
                //判断用户余额是否充足
if(Number(this.data.userInfo.money) < Number(item.price * item.buynumber)){

wx.showToast({
title: '余额不足，请前往充值',
icon: "none"
})
return
}
                //减去用户余额
                this.setData({
                    'userInfo.money': (parseFloat(this.data.userInfo.money) - parseFloat(item.price * item.buynumber)).toFixed(2)
                })
                const userpayed = await update(table, this.data.userInfo)

                userpayed.code == 0 && this.data.types == 3 ? data['status'] = '拼团中' : ( userpayed.code == 0?data['status'] = '已支付':"")
                //新增订单
               await add("orders", data)



    //如果商品有库存 减去商品库存
    if (infodata.alllimittimes) {
        infodata.alllimittimes = infodata.alllimittimes - item.buynumber
    }
    await update(item.tablename,infodata)

                const params={
                    page:1,
                    limit:1,
                    orderid: orderId
                }
                          const listRes= await list(item.tablename,params)

                listRes.data.list[0].status = '已支付'
    userpayed.code == 0 && this.data.types == 3 ? data['status'] = '拼团中' : ( userpayed.code == 0?listRes.data.list[0].status = '已支付':"")
            const ordersRes=       await update('orders',listRes.data.list[0])
            if(item.id){
            await       deleteData('cart',[item.id])
            }
    //下单完成，跳转订单
    wx.showToast({
        title: '购买成功',
        icon: "none"
    })
setTimeout(function () {
    wx.navigateBack({
        delta: 1,
        complete: () => {
            wx.removeStorageSync('address')

            // 触发事件通知，传递需要更新的数据
            const pages = getCurrentPages();
            if (pages.length >= 1) {
                const prePage = pages[pages.length - 1];
                prePage.onLoad(); //
            }
        }
    })
}, 2000)


            }
        })
    },

    //沙箱支付
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        this.onLoad()
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