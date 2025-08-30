
Page({

    /**
     * 页面的初始数据
     */
    data: {

    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        setTimeout(function(){
            wx.navigateBack({
                delta: 2,
                success: (res) => {},
                fail: (res) => {},
                complete: (res) => {},
            })
        },2000)

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {
        // 微信小程序点击自带的左上角返回事件
    },
    back(){
        wx.navigateBack({
            delta: 2,
            success: (res) => {},
            fail: (res) => {},
            complete: (res) => {},
        })
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