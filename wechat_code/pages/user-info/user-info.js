const dateUtils = require('../../utils/defautils')
const menuData=require('../../utils/menu.js')
const {
SendverificationCode,
option,
follow,wxbind,wxunbind
} = require('../../api/login.js')
const {
levelOption,
sheng,
update,
session
} = require('../../api/index.js')
const utils = require("../../utils/index.js")
Page({
data: {
code:'',
openid:'',
tableName:"",
    userInfo:'',
ro:{
},
huiyuanzhanghao:'',
mima:'',
huiyuanxingming:'',
shouji:'',
touxiang:'../../static/upload.png',
tempPathtouxiang:'../../static/upload.png',
money:'',
yuangongzhanghao:'',
mima:'',
xingming:'',
lianxifangshi:'',
touxiang:'../../static/upload.png',
tempPathtouxiang:'../../static/upload.png',

registerContainerClass: "",
role:""

},

async onLoad() {

wx.login({
provider: 'weixin',
success: function (res) {
let code = res.code;
if (code) {
this.setData({
code
});
}
}.bind(this)
});
let tableName = wx.getStorageSync("nowTable");
const menu = menuData.default.list()
const name = wx.getStorageSync("role")
let role
menu.map(obj => {
if (name == obj.roleName) {
role = obj.tableName
}
})

const  userInfo  = getApp().globalData.userInfo
    this.setData({
        role,
        tableName,
        userInfo
    })
const   baseURL= wx.getStorageSync('baseURL')+"/"
    let userInfoObj={}
    userInfoObj["huiyuanzhanghao"]= userInfo.huiyuanzhanghao==null?"":userInfo.huiyuanzhanghao
    userInfoObj["mima"]= userInfo.mima==null?"":userInfo.mima
    userInfoObj["huiyuanxingming"]= userInfo.huiyuanxingming==null?"":userInfo.huiyuanxingming
this.setData({
shouji: userInfo.shouji,
})
userInfo['touxiang']=userInfo?.touxiang?.replace('upload','file')
this.setData({
touxiang:baseURL+userInfo.touxiang,
tempPathtouxiang:baseURL+userInfo.touxiang,
})
    userInfoObj["money"]= userInfo.money==null?"":userInfo.money
    userInfoObj["yuangongzhanghao"]= userInfo.yuangongzhanghao==null?"":userInfo.yuangongzhanghao
    userInfoObj["mima"]= userInfo.mima==null?"":userInfo.mima
    userInfoObj["xingming"]= userInfo.xingming==null?"":userInfo.xingming
this.setData({
lianxifangshi: userInfo.lianxifangshi,
})
userInfo['touxiang']=userInfo?.touxiang?.replace('upload','file')
this.setData({
touxiang:baseURL+userInfo.touxiang,
tempPathtouxiang:baseURL+userInfo.touxiang,
})
    this.setData(
        userInfoObj
    )
//ss读取

},
async onShow() {
},
async sendCodeHandler() {
if (!this.data.canSendCode) {
return;
}
if (this.data.shouji == "") {
wx.showToast({
title: '请输入手机',
icon: 'none'
})
return;
} else {
if (validatePhoneNumber(this.data.shouji) == false) {
wx.showToast({
title: '请输入有效手机',
icon: 'none'
})
return;
} else {
const res = await SendverificationCode("${tableName}", 'sendsms','shouji',  this.data.shouji)
this.setData({
smscode: res.data
})
}
}
this.setData({
canSendCode: false,
});
let time = this.data.countdown;
let timer = setInterval(() => {
time--;
this.setData({
countdown: time, // 更新倒计时的时间
});
if (time <= 0) {
clearInterval(timer); // 倒计时结束，清除定时器
this.setData({
buttonText: '发送验证码',
canSendCode: true,
countdown: 60
});
}
}, 1000);

},
touxiangTap() {
wx.chooseImage({
count: 1,
sizeType: ['compressed'],
sourceType: ['album', 'camera'],
success: async (res) => {
const tempFilePaths = res.tempFilePaths;
// 本地临时图片的路径
this.setData({
tempPathtouxiang: tempFilePaths[0]
})
// 上传网络图片
const  baseURL=  wx.getStorageSync("baseURL")
wx.uploadFile({
url: `${baseURL}/file/upload`,
filePath: res.tempFilePaths[0],
name: 'file',
header: {
'Token': wx.getStorageSync('token')
},
success: (uploadFileRes) => {
let result = JSON.parse(uploadFileRes.data);
// result.file是上传成功为网络图片的名称
if (result.code == 0) {
this.setData({
        touxiang: 'file/' + result.file
})
} else {
wx.showToast({
    title: result.msg,
    icon: 'none',
    duration: 2000
});
}
}
})



}
})
},
async sendCodeHandler() {
if (!this.data.canSendCode) {
return;
}
if (this.data.lianxifangshi == "") {
wx.showToast({
title: '请输入联系方式',
icon: 'none'
})
return;
} else {
if (validatePhoneNumber(this.data.lianxifangshi) == false) {
wx.showToast({
title: '请输入有效联系方式',
icon: 'none'
})
return;
} else {
const res = await SendverificationCode("${tableName}", 'sendsms','lianxifangshi',  this.data.lianxifangshi)
this.setData({
smscode: res.data
})
}
}
this.setData({
canSendCode: false,
});
let time = this.data.countdown;
let timer = setInterval(() => {
time--;
this.setData({
countdown: time, // 更新倒计时的时间
});
if (time <= 0) {
clearInterval(timer); // 倒计时结束，清除定时器
this.setData({
buttonText: '发送验证码',
canSendCode: true,
countdown: 60
});
}
}, 1000);

},
touxiangTap() {
wx.chooseImage({
count: 1,
sizeType: ['compressed'],
sourceType: ['album', 'camera'],
success: async (res) => {
const tempFilePaths = res.tempFilePaths;
// 本地临时图片的路径
this.setData({
tempPathtouxiang: tempFilePaths[0]
})
// 上传网络图片
const  baseURL=  wx.getStorageSync("baseURL")
wx.uploadFile({
url: `${baseURL}/file/upload`,
filePath: res.tempFilePaths[0],
name: 'file',
header: {
'Token': wx.getStorageSync('token')
},
success: (uploadFileRes) => {
let result = JSON.parse(uploadFileRes.data);
// result.file是上传成功为网络图片的名称
if (result.code == 0) {
this.setData({
        touxiang: 'file/' + result.file
})
} else {
wx.showToast({
    title: result.msg,
    icon: 'none',
    duration: 2000
});
}
}
})



}
})
},
quitTap(){
let saveBaseURL = wx.getStorageSync('baseURL')

wx.clearStorageSync();
wx.setStorageSync('baseURL', saveBaseURL)
wx.reLaunch({
url: "/pages/login/login"
});
},
async  saveTap(){


const baseURL = wx.getStorageSync('baseURL') + "/"
const regex = new RegExp(baseURL, "g");
const resultObj={
huiyuanzhanghao:this.data.huiyuanzhanghao,
mima:this.data.mima,
mima2:this.data.mima2,
huiyuanxingming:this.data.huiyuanxingming,
shouji:this.data.shouji,
touxiang:this.data.touxiang.replace(regex, ""),
money:this.data.money,
yuangongzhanghao:this.data.yuangongzhanghao,
mima:this.data.mima,
mima2:this.data.mima2,
xingming:this.data.xingming,
lianxifangshi:this.data.lianxifangshi,
touxiang:this.data.touxiang.replace(regex, ""),
id:getApp().globalData.userInfo.id

}
const res = await update(this.data.role, resultObj)
if (res.code == 0) {
const userInfoRes = await session(this.data.role)
if (userInfoRes.code == 0) {
getApp().globalData.userInfo = userInfoRes.data
wx.reLaunch({
url: '/pages/center/center',
})
}
} else {
wx.showToast({
title: res.msg,
icon: "none"
})
}

},
async   getSession(){
let res = await session(this.data.role)
getApp().globalData.userInfo=res.data
this.setData({
openid:res.data.openid
})
}
});