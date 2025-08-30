Page({
    data: {
        dataList: [{
            content: '包裹已揽收',
            time: '2022-03-25 16:15'
        },
            {
                content: '包裹正在等待揽收',
            },
            {
                content: '商品已下单',
            }
        ],
        info: {}
    },
    onLoad() {
        const info = wx.getStorageSync('logistics')


        if (info.status == "已支付" || info.logistics) {
            //处理富文本内容不要标签和图片，只要文字
            let result = info.logistics.replace(/<[^>]*>/g, '')
            this.setData({
                info: info,
                'dataList[0]': {
                    content: `快件发往${result}`,
                    time: info.addtime
                },
            })
        }
        if (info.status == "已发货") {
            const dataList = this.data.dataList
            dataList.unshift({
                content: '快件已到达',
                time: info.addtime
            }, )
            this.setData({
                dataList
            })
        }
    }
})