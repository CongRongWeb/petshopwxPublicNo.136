const menu = {
  list() {
    return [{
      "backMenu": [{
        "child": [{
          "appFrontIcon": "cuIcon-goodsnew",
          "buttons": ["查看", "修改", "删除"],
          "menu": "操作日志",
          "menuJump": "列表",
          "tableName": "syslog"
        }],
        "fontClass": "icon-common15",
        "menu": "操作日志管理",
        "unicode": "&#xedfc;"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-send",
          "buttons": ["查看", "修改", "删除"],
          "menu": "客服聊天",
          "menuJump": "列表",
          "tableName": "chat"
        }],
        "fontClass": "icon-common39",
        "menu": "客服聊天管理",
        "unicode": "&#xeeba;"
      },
      {
        "child": [{
          "appFrontIcon": "cuIcon-attentionfavor",
          "buttons": ["查看", "修改", "删除", "查看评论"],
          "menu": "论坛交流",
          "tableName": "forum"
        }],
        "fontClass": "icon-common34",
        "menu": "论坛管理",
        "unicode": "&#xee85;"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-goods",
          "buttons": ["新增", "查看", "修改", "删除", "查看评论"],
          "menu": "服务",
          "menuJump": "列表",
          "tableName": "fuwu"
        }, {
          "appFrontIcon": "cuIcon-wenzi",
          "buttons": ["新增", "查看", "修改", "删除"],
          "menu": "服务类型",
          "menuJump": "列表",
          "tableName": "fuwuleixing"
        }, {
          "appFrontIcon": "cuIcon-form",
          "buttons": ["查看", "修改", "删除"],
          "menu": "预约服务",
          "menuJump": "列表",
          "tableName": "yuyuefuwu"
        }],
        "fontClass": "icon-common38",
        "menu": "服务管理",
        "unicode": "&#xeeb2;"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-list",
          "buttons": ["查看", "首页统计"],
          "menu": "寄养订单",
          "menuJump": "列表",
          "tableName": "jiyangdingdan"
        }, {
          "appFrontIcon": "cuIcon-keyboard",
          "buttons": ["新增", "查看", "修改", "删除"],
          "menu": "寄养价格",
          "menuJump": "列表",
          "tableName": "jiyangjiage"
        }],
        "fontClass": "icon-common46",
        "menu": "寄养订单管理",
        "unicode": "&#xef3d;"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-list",
          "buttons": ["新增", "查看", "修改", "删除", "查看评论", "商品进库"],
          "menu": "商品",
          "menuJump": "列表",
          "tableName": "shangpin"
        }, {
          "appFrontIcon": "cuIcon-paint",
          "buttons": ["查看", "修改", "删除", "新增"],
          "menu": "商品类型",
          "menuJump": "列表",
          "tableName": "shangpinleixing"
        }, {
          "appFrontIcon": "cuIcon-cardboard",
          "buttons": ["查看", "修改", "删除"],
          "menu": "商品进库",
          "menuJump": "列表",
          "tableName": "shangpinjinku"
        }],
        "fontClass": "icon-common20",
        "menu": "商品管理",
        "unicode": "&#xee02;"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-form",
          "buttons": ["新增", "查看", "修改", "删除"],
          "menu": "员工",
          "menuJump": "列表",
          "tableName": "yuangong"
        }],
        "fontClass": "icon-user3",
        "menu": "员工管理",
        "unicode": "&#xef99;"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-newshot",
          "buttons": ["新增", "查看", "修改", "删除"],
          "menu": "会员",
          "menuJump": "列表",
          "tableName": "huiyuan"
        }],
        "fontClass": "icon-common49",
        "menu": "会员管理",
        "unicode": "&#xef79;"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-pic",
          "buttons": ["查看", "修改"],
          "menu": "轮播图",
          "menuJump": "列表",
          "tableName": "config"
        }],
        "fontClass": "icon-common48",
        "menu": "轮播图管理",
        "unicode": "&#xef65;"
      }],
      "frontMenu": [{
        "child": [{
          "appFrontIcon": "cuIcon-medal",
          "buttons": ["查看评论"],
          "menu": "商品",
          "menuJump": "列表",
          "tableName": "shangpin"
        }],
        "menu": "商品管理"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-vipcard",
          "buttons": ["预约服务", "查看评论"],
          "menu": "服务",
          "menuJump": "列表",
          "tableName": "fuwu"
        }],
        "menu": "服务管理"
      }],
      "hasBackLogin": "是",
      "hasBackRegister": "否",
      "hasFrontLogin": "否",
      "hasFrontRegister": "否",
      "roleName": "管理员",
      "tableName": "users"
    }, {
      "backMenu": [{
        "child": [{
          "appFrontIcon": "cuIcon-flashlightopen",
          "buttons": ["新增", "查看", "修改", "删除"],
          "menu": "我的收藏",
          "menuJump": "1",
          "tableName": "storeup"
        }],
        "fontClass": "icon-common39",
        "menu": "我的收藏管理",
        "unicode": "&#xeeba;"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-keyboard",
          "buttons": ["新增", "查看", "修改", "删除"],
          "menu": "地址",
          "menuJump": "列表",
          "tableName": "address"
        }],
        "fontClass": "icon-common19",
        "menu": "地址管理",
        "unicode": "&#xee00;"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-wenzi",
          "buttons": ["新增", "查看", "修改", "删除"],
          "menu": "购物车",
          "menuJump": "列表",
          "tableName": "cart"
        }],
        "fontClass": "icon-common8",
        "menu": "购物车管理",
        "unicode": "&#xedb6;"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-send",
          "buttons": ["新增", "查看", "修改", "删除"],
          "menu": "客服聊天",
          "menuJump": "列表",
          "tableName": "chat"
        }],
        "fontClass": "icon-common39",
        "menu": "客服聊天管理",
        "unicode": "&#xeeba;"
      },
       {
        "child": [{
          "appFrontIcon": "cuIcon-attentionfavor",
          "buttons": ["新增", "查看", "修改", "删除"],
          "menu": "我的发布",
          "menuJump": "14",
          "tableName": "forum"
        }],
        "fontClass": "icon-common34",
        "menu": "论坛管理",
        "unicode": "&#xee85;"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-form",
          "buttons": ["查看", "支付"],
          "menu": "预约服务",
          "menuJump": "列表",
          "tableName": "yuyuefuwu"
        }],
        "fontClass": "icon-common38",
        "menu": "服务管理",
        "unicode": "&#xeeb2;"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-list",
          "buttons": ["查看", "新增", "修改", "删除"],
          "menu": "寄养订单",
          "menuJump": "列表",
          "tableName": "jiyangdingdan"
        }, {
          "appFrontIcon": "cuIcon-pic",
          "buttons": ["查看", "删除"],
          "menu": "寄养日志",
          "menuJump": "列表",
          "tableName": "jiyangrizhi"
        }],
        "fontClass": "icon-common46",
        "menu": "寄养订单管理",
        "unicode": "&#xef3d;"
      }],
      "frontMenu": [{
        "child": [{
          "appFrontIcon": "cuIcon-medal",
          "buttons": ["查看评论"],
          "menu": "商品",
          "menuJump": "列表",
          "tableName": "shangpin"
        }],
        "menu": "商品管理"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-vipcard",
          "buttons": ["预约服务", "查看评论"],
          "menu": "服务",
          "menuJump": "列表",
          "tableName": "fuwu"
        }],
        "menu": "服务管理"
      }],
      "hasBackLogin": "否",
      "hasBackRegister": "否",
      "hasFrontLogin": "是",
      "hasFrontRegister": "是",
      "roleName": "会员",
      "tableName": "huiyuan"
    }, {
      "backMenu": [{
        "child": [{
          "appFrontIcon": "cuIcon-goods",
          "buttons": ["新增", "查看", "修改", "删除", "查看评论"],
          "menu": "服务",
          "menuJump": "列表",
          "tableName": "fuwu"
        }, {
          "appFrontIcon": "cuIcon-wenzi",
          "buttons": ["新增", "查看", "修改", "删除"],
          "menu": "服务类型",
          "menuJump": "列表",
          "tableName": "fuwuleixing"
        }, {
          "appFrontIcon": "cuIcon-form",
          "buttons": ["查看"],
          "menu": "预约服务",
          "menuJump": "列表",
          "tableName": "yuyuefuwu"
        }],
        "fontClass": "icon-common38",
        "menu": "服务管理",
        "unicode": "&#xeeb2;"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-list",
          "buttons": ["查看", "寄养日志"],
          "menu": "寄养订单",
          "menuJump": "列表",
          "tableName": "jiyangdingdan"
        }, {
          "appFrontIcon": "cuIcon-pic",
          "buttons": ["查看", "修改"],
          "menu": "寄养日志",
          "menuJump": "列表",
          "tableName": "jiyangrizhi"
        }, {
          "appFrontIcon": "cuIcon-keyboard",
          "buttons": ["新增", "查看", "修改", "删除"],
          "menu": "寄养价格",
          "menuJump": "列表",
          "tableName": "jiyangjiage"
        }],
        "fontClass": "icon-common46",
        "menu": "寄养订单管理",
        "unicode": "&#xef3d;"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-list",
          "buttons": ["新增", "查看", "修改", "删除", "查看评论", "商品进库"],
          "menu": "商品",
          "menuJump": "列表",
          "tableName": "shangpin"
        }, {
          "appFrontIcon": "cuIcon-paint",
          "buttons": ["新增", "查看", "修改", "删除"],
          "menu": "商品类型",
          "menuJump": "列表",
          "tableName": "shangpinleixing"
        }, {
          "appFrontIcon": "cuIcon-cardboard",
          "buttons": ["查看"],
          "menu": "商品进库",
          "menuJump": "列表",
          "tableName": "shangpinjinku"
        }],
        "fontClass": "icon-common20",
        "menu": "商品管理",
        "unicode": "&#xee02;"
      }],
      "frontMenu": [{
        "child": [{
          "appFrontIcon": "cuIcon-medal",
          "buttons": ["查看评论"],
          "menu": "商品",
          "menuJump": "列表",
          "tableName": "shangpin"
        }],
        "menu": "商品管理"
      }, {
        "child": [{
          "appFrontIcon": "cuIcon-vipcard",
          "buttons": ["预约服务", "查看评论"],
          "menu": "服务",
          "menuJump": "列表",
          "tableName": "fuwu"
        }],
        "menu": "服务管理"
      }],
      "hasBackLogin": "是",
      "hasBackRegister": "否",
      "hasFrontLogin": "否",
      "hasFrontRegister": "否",
      "roleName": "员工",
      "tableName": "yuangong"
    }]
  }
}
export default menu;