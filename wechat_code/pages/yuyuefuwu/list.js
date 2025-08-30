const {
deleteData,
page,
list,
newsData,
option,
} = require("../../api/index.js")
const utils = require("../../utils/index.js")
Page({
/**
* 页面的初始数据
*/
data: {

    

    questList: [],
    pageNum: 1,
    pageSize: 10,
    total: 0,
    flag: true, // 防抖开关 防止用户不停的下拉


showToTopButton: true,
onPageScrollTop: 0, // 存储滚动距离的变量

goodsListData: [],
activeIndex: 0,
allData: [],
deleteShow: false,
className:"",
name:"",
addAuth:"",
delAuth: "",
editAuth:"" ,
userid:"",
baseURL: wx.getStorageSync('baseURL') + "/",
isAuthStatus:false
},

/**
* 生命周期函数--监听页面加载
*/
async onLoad(options) {
    this.setData({
        isAuthStatus: wx.getStorageSync('isAuth')
    })
if(options?.userid) {
this.setData({
    userid:options.userid
})

}
const currentPageUrl = this.getCurrentPageUrl();
if(getApp().globalData.name!=null){
this.setData({
    name: getApp().globalData.name
})
getApp().globalData.name=null
this.searhandler()
}else{
this.getData()
}



},

onShow() {
// 获取当前页面栈
const pages = getCurrentPages();
// 获取当前页面对象
const currentPage = pages[pages.length - 1];
// 获取当前页面的显示报告对象
let displayReporter = currentPage.__displayReporter;
if(displayReporter&& displayReporter.showReferpagepath .includes('detail')){
}else{
if (getApp().globalData.name == null) {
    this.setData({
        name: null
    })
    this.setData({
        isAuthStatus: wx.getStorageSync('isAuth')
    })
    this.getData()
}
const currentPageUrl = this.getCurrentPageUrl();
if(getApp().globalData.name){
    this.setData({
        name: getApp().globalData.name
    })
    getApp().globalData.name=null
    this.searhandler()
}else{
    this.getData()
}
}


},
/**
* 生命周期函数--监听页面初次渲染完成
*/
onPageShow() {
// 页面显示时执行的操作
},



screenReset(){
    let obj={}

    this.setData(obj)

},





async   search(){
const nowTable=wx.getStorageSync('nowTable')
let searchForm = {
page: this.data.pageNum,
limit: this.data.pageSize
}


if(this.data.fuwubiaoti){
    searchForm['fuwubiaoti'] = '%' + this.data.fuwubiaoti + '%'
}
let res = {};
if(this.data.userid) {

res = await page(`yuyuefuwu`, searchForm);
} else {
res = await list(`yuyuefuwu`, searchForm);
}


let goodsListData
// 如果是第一页数据置空
 if ( this.data.pageNum == 1) {
     goodsListData = []
 };
    goodsListData=res.data.list.map(item=>{
item.fuwutupian = item.fuwutupian.split(",")[0];
        return item
    })


this.setData({
goodsListData,
popopShow:false
})

},

// 搜索








/**
* 生命周期函数--监听页面显示
*/
getCurrentPageUrl() {
const pages = getCurrentPages();
const currentPage = pages[pages.length - 1];
const currentPageUrl = `/${currentPage.route}`;
return currentPageUrl;
},
    async  handleTabClick(e) {
        let params={
        }
        const nowTable=  wx.getStorageSync('nowTable')

this.setData({
activeIndex: index,
});
},
    async searhandler(){
        let token = wx.getStorageSync('token')
        if (!token) {
            return
        }
let targetName="fuwubiaoti"
        const allData=this.data.allData

        let goodsListData

        if(this.data.name==''){
            goodsListData=allData
        }else{
            goodsListData = allData.filter(item => item[targetName].includes(this.data.name));

        }
        this.setData({
            goodsListData
        })



    },
addTap() {
getApp().globalData.detailId=null
wx.navigateTo({
url: `/pages/yuyuefuwu/update-and-add`
})
if(this.data.isAuthStatus) {
setTimeout(function () {
    wx.setStorageSync('isAuth', true)
}, 1000)
}
},
searchListHandler(e) {
this.setData({
goodsListData: e.detail.data
})
},
onPageScroll(e) {
if (e.scrollTop >= 225) {
this.setData({
    showToTopButton: true
});
}

},
backToTop() {
wx.pageScrollTo({
scrollTop: 0, // 返回顶部的位置
duration: 1000, // 滚动动画的时长，单位为 ms
});
// 返回顶部时隐藏按钮

},

deleteBtn(e) {
wx.showModal({
title: '提示',
content: '确认删除？',
complete: async (res) => {
    if (res.cancel) {}
    if (res.confirm) {
        const id = e.currentTarget.dataset.id;
        const res = await deleteData("yuyuefuwu",[id])
        console.log(res);
        if (res.code == 0) {
            this.getData()
        }
    }
}
})
},

editBtn(e) {
const id = e.currentTarget.dataset.id;
getApp().globalData.detailId=id
wx.navigateTo({
url: `/pages/yuyuefuwu/update-and-add?id=${id}&isAuth=${this.data.isAuthStatus}`
})
},
async detailBtn(e) {
const item = e.currentTarget.dataset.item;
getApp().globalData.detailId = item?.id
getApp().globalData.detailList =item
wx.navigateTo({
    url: `/pages/yuyuefuwu/detail?isAuth=${this.data.isAuthStatus}`
})
if(this.data.isAuthStatus) {
setTimeout(function () {
    wx.setStorageSync('isAuth', true)
}, 1000)
}
},


     /**
      * 页面上拉触底事件的处理函数
      */
     onReachBottom() {
         if (this.data.flag) {
             this.setData({
                 flag: false
             })
             this.getData(); // 疯狂的请求的方法
         }
     },


     async getData() {
    const obj={
        page: this.data.pageNum,
        limit: this.data.pageSize,
            }
    const nowTable=wx.getStorageSync('nowTable')
const userId=       getApp().globalData.userInfo.id
        let isAuthObj={}
if(this.data.isAuthStatus){
isAuthObj['addAuth']   = utils.isAuth("yuyuefuwu", "新增")
isAuthObj['delAuth']  = utils.isAuth("yuyuefuwu", "删除")
isAuthObj['editAuth']   =utils.isAuth("yuyuefuwu", "修改")

}else{
isAuthObj['addAuth']   = utils.isAuthFront("yuyuefuwu", "新增")
isAuthObj['delAuth']  = utils.isAuthFront("yuyuefuwu", "删除")
isAuthObj['editAuth']   =utils.isAuthFront("yuyuefuwu", "修改")
}
//前后台权限判断
this.setData(isAuthObj)











let resList
var that = this
obj['page']=this.data.pageNum;
obj['limit']=this.data.pageSize
    if(this.data.userid){
        resList = await page("yuyuefuwu",obj)
    }else{
         resList= await list("yuyuefuwu",obj)
    }

    if(resList.code==0){
        let mylist = this.data.questList
        //先处理成你想要的数据 下面再去赋值

        let    goodsListData =  resList.data.list.map(item => {
item.fuwutupian? item.fuwutupian = item.fuwutupian.split(",")[0].replace('upload','flie'):""
return item;
});
        if (that.data.pageNum == 1) {
            mylist = []
        }

        // 新旧数据合并到一起
        mylist = Object.assign(mylist, goodsListData)
        let resultdata
        if(resList.data.list.length>0){
            resultdata =  Object.assign(mylist,resList.data.list)
        }
        if (mylist.length < resList.data.total ){
            that.setData({
                pageNum:obj.page+ 1,
                flag:true
            })
        } else {
            that.setData({
                flag:false
            })
        }
        that.setData({
            goodsListData: resultdata,
            questList: mylist,
            total: resList.total,
            allData: mylist
        })
        
    }






// 商品名称
if (getApp().globalData.name != null) {
this.setData({
goodsListData: getApp().globalData.goodsList,
})
getApp().globalData.name = null
console.log('页面重新加载');
}

},

// 滚动到底触发的方法

/**
* 生命周期函数--监听页面隐藏
*/
onHide() {
    wx.removeStorageSync('isAuth');
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
* 用户点击右上角分享
*/
onShareAppMessage() {

}
})