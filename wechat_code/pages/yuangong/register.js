
const dateUtils = require('../../utils/defautils')
const utils = require("../../utils/index.js")
const {
SendverificationCode,
register,
option,
    smscode,
follow
} = require('../../api/login.js')
const {
    levelOption,
    sheng,
} = require('../../api/index.js')
Page({
data: {
     yuangongzhanghao:'',
     mima:'',
     xingming:'',
     lianxifangshi:'',
    touxiang:'',
    tempPathtouxiang:'../../static/upload.png',

    registerContainerClass: "",

},

async onLoad() {








},
onUnload() {
},
async onShow() {






},







async  register(){
if (this.data.yuangongzhanghao == "") {
wx.showToast({
title: '请输入员工账号',
icon: "none"
})
return;
}
if (this.data.mima == "") {
wx.showToast({
title: '请输入密码',
icon: "none"
})
return;
}
if (this.data.mima2=="") {
wx.showToast({
title: '请输入确认密码',
icon: "none"
})
return;
}
if (this.data.mima !== this.data.mima2) {
wx.showToast({
title: '密码与确认密码不一致!!',
icon: "none"
})
return;
}
if (this.data.lianxifangshi == "") {
wx.showToast({
title: '请输入联系方式',
icon: "none"
})
return;
}
        if (!utils.validata("lianxifangshi",this.data.lianxifangshi)) {
        wx.showToast({
            title: '请输入有效联系方式',
            icon: 'none'
        })
        return;
    }









    const regex = new RegExp(wx.getStorageSync("baseURL"), "g");
  const resultObj={
        yuangongzhanghao:this.data.yuangongzhanghao,
        mima:this.data.mima,
        mima2:this.data.mima2,
        xingming:this.data.xingming,
        lianxifangshi:this.data.lianxifangshi,
        touxiang:this.data.touxiang.replace(regex, ""),
  }
    const name="yuangongzhanghao"
    const password="mima"
    const res = await register("yuangong", name, this.data[name],password , this.data[password], resultObj)
if (res.code == 0) {
wx.navigateTo({
url: '../login/login',
})
} else {
wx.showToast({
title: res.msg,
icon: "none"
})
}

}



});