<template>
  <div class="home_view">
    <div class="projectTitle">欢迎使用 {{ projectName }}</div>
    <div class="card_list">
      <el-collapse-transition v-if="btnAuth('orders', '首页统计')">
        <el-card v-show="cardTypeList.closeordersChartType1" class="card_view">
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">商品订单</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenordersChartType1')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenordersChartType1 ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closeordersChartType1')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div class="card_item" v-show="cardTypeList.hiddenordersChartType1">
              <div
                id="orderstotalEchart1"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('orders', '首页统计')">
        <el-card v-show="cardTypeList.closeordersChartType2" class="card_view">
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">商品订单</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenordersChartType2')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenordersChartType2 ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closeordersChartType2')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div class="card_item" v-show="cardTypeList.hiddenordersChartType2">
              <div
                id="ordersbuynumberEchart2"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('orders', '首页统计')">
        <el-card v-show="cardTypeList.closeordersChartType3" class="card_view">
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">商品订单</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenordersChartType3')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenordersChartType3 ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closeordersChartType3')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div class="card_item" v-show="cardTypeList.hiddenordersChartType3">
              <div
                id="orderstotalEchart3"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('jiyangdingdan', '首页统计')">
        <el-card
          v-show="cardTypeList.closejiyangdingdanChartType1"
          class="card_view"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">寄养订单</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenjiyangdingdanChartType1')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenjiyangdingdanChartType1
                      ? 'showIcons1'
                      : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closejiyangdingdanChartType1')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="card_item"
              v-show="cardTypeList.hiddenjiyangdingdanChartType1"
            >
              <div
                id="jiyangdingdanxingmingEchart1"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('jiyangdingdan', '首页统计')">
        <el-card
          v-show="cardTypeList.closejiyangdingdanChartType2"
          class="card_view"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">寄养订单</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenjiyangdingdanChartType2')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenjiyangdingdanChartType2
                      ? 'showIcons1'
                      : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closejiyangdingdanChartType2')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="card_item"
              v-show="cardTypeList.hiddenjiyangdingdanChartType2"
            >
              <div
                id="jiyangdingdanzongjiaEchart2"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
    </div>
  </div>
</template>

<script setup>
import { inject, nextTick, ref, getCurrentInstance } from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
const projectName = context.$project.projectName
const init = () => {
  getCardList()
}
//权限验证
const btnAuth = (e, a) => {
  return context?.$toolUtil.isAuth(e, a)
}
let echarts = inject('echarts')
const cardTypeClick = (e) => {
  cardTypeList.value[e] = !cardTypeList.value[e]
  setTimeout(() => {
    getCardList()
  }, 1000)
}
const cardTypeList = ref({
  closeordersChartType1: true,
  hiddenordersChartType1: true,
  closeordersChartType2: true,
  hiddenordersChartType2: true,
  closeordersChartType3: true,
  hiddenordersChartType3: true,
  closejiyangdingdanChartType1: true,
  hiddenjiyangdingdanChartType1: true,
  closejiyangdingdanChartType2: true,
  hiddenjiyangdingdanChartType2: true,
})
const getCardList = () => {
  if (btnAuth('orders', '首页统计')) {
    getordersChart1()
  }
  if (btnAuth('orders', '首页统计')) {
    getordersChart2()
  }
  if (btnAuth('orders', '首页统计')) {
    getordersChart3()
  }
  if (btnAuth('jiyangdingdan', '首页统计')) {
    getjiyangdingdanChart1()
  }
  if (btnAuth('jiyangdingdan', '首页统计')) {
    getjiyangdingdanChart2()
  }
}
const getordersChart1 = () => {
  nextTick(() => {
    var totalEchart1 = echarts.init(
      document.getElementById('orderstotalEchart1'),
      'macarons'
    )
    context
      ?.$http({
        url: `orders/value/addtime/total/月`,
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let pArray = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].addtime)
          yAxis.push(parseFloat(res[i].total))
          pArray.push({
            value: parseFloat(res[i].total),
            name: res[i].addtime,
          })
        }
        var option = {}
        option = {
          title: {
            text: '商品销售额统计',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            type: 'value',
          },
          yAxis: {
            type: 'category',
            data: xAxis,
          },
          series: [
            {
              data: yAxis,
              type: 'bar',
            },
          ],
        }
        totalEchart1.clear()
        // 使用刚指定的配置项和数据显示图表。
        totalEchart1.setOption(option)
        //根据窗口的大小变动图表
        totalEchart1.resize()
      })
  })
}
const getordersChart2 = () => {
  nextTick(() => {
    var buynumberEchart2 = echarts.init(
      document.getElementById('ordersbuynumberEchart2'),
      'macarons'
    )
    context
      ?.$http({
        url: `orders/value/goodname/buynumber`,
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let pArray = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].goodname)
          yAxis.push(parseFloat(res[i].total))
          pArray.push({
            value: parseFloat(res[i].total),
            name: res[i].goodname,
          })
        }
        var option = {}
        option = {
          title: {
            text: '商品销量排行',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            type: 'category',
            data: xAxis,
            axisLabel: {
              rotate: 40,
            },
          },
          yAxis: {
            type: 'value',
          },
          series: [
            {
              data: yAxis,
              type: 'bar',
            },
          ],
        }
        // 使用刚指定的配置项和数据显示图表。
        buynumberEchart2.clear()
        buynumberEchart2.setOption(option)
        //根据窗口的大小变动图表
        buynumberEchart2.resize()
      })
  })
}
const getordersChart3 = () => {
  nextTick(() => {
    //  total
    var totalEchart3 = echarts.init(
      document.getElementById('orderstotalEchart3'),
      'macarons'
    )
    context
      ?.$http({
        url: `orders/value/goodname/total`,
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let pArray = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].goodname)
          yAxis.push(parseFloat(res[i].total))
          pArray.push({
            value: parseFloat(res[i].total),
            name: res[i].goodname,
          })
        }
        var option = {}
        option = {
          title: {
            text: '商品销售额统计',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: xAxis,
          },
          yAxis: {
            type: 'value',
          },
          series: [
            {
              data: yAxis,
              type: 'line',
              smooth: true,
            },
          ],
        }
        // 使用刚指定的配置项和数据显示图表。
        totalEchart3.clear()
        totalEchart3.setOption(option)
        //根据窗口的大小变动图表
        totalEchart3.resize()
      })
  })
}
const getjiyangdingdanChart1 = () => {
  nextTick(() => {
    var xingmingEchart1 = echarts.init(
      document.getElementById('jiyangdingdanxingmingEchart1'),
      'macarons'
    )
    context
      ?.$http({
        url: 'jiyangdingdan/group/xingming',
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let pArray = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].xingming)
          yAxis.push(parseFloat(res[i].total))
          pArray.push({
            value: parseFloat(res[i].total),
            name: res[i].xingming,
          })
        }
        var option = {}
        option = {
          title: {
            text: '业绩统计',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            type: 'category',
            data: xAxis,
            axisLabel: {
              rotate: 40,
            },
          },
          yAxis: {
            type: 'value',
          },
          series: [
            {
              data: yAxis,
              type: 'bar',
            },
          ],
        }
        xingmingEchart1.clear()
        // 使用刚指定的配置项和数据显示图表。
        xingmingEchart1.setOption(option)
        //根据窗口的大小变动图表
        xingmingEchart1.resize()
      })
  })
}
const getjiyangdingdanChart2 = () => {
  nextTick(() => {
    var zongjiaEchart2 = echarts.init(
      document.getElementById('jiyangdingdanzongjiaEchart2'),
      'macarons'
    )
    context
      ?.$http({
        url: `jiyangdingdan/value/jiyangjieshushijian/zongjia/月`,
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let pArray = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].jiyangjieshushijian)
          yAxis.push(parseFloat(res[i].total))
          pArray.push({
            value: parseFloat(res[i].total),
            name: res[i].jiyangjieshushijian,
          })
        }
        var option = {}
        option = {
          title: {
            text: '寄养收入统计',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: xAxis,
          },
          yAxis: {
            type: 'value',
          },
          series: [
            {
              data: yAxis,
              type: 'line',
            },
          ],
        }
        // 使用刚指定的配置项和数据显示图表。
        zongjiaEchart2.clear()
        zongjiaEchart2.setOption(option)
        //根据窗口的大小变动图表
        zongjiaEchart2.resize()
      })
  })
}
init()
</script>
<style lang="scss">
.projectTitle {
  padding: 20px 0;
  margin: 20px 0 20px;
  font-weight: bold;
  display: flex;
  width: 100%;
  font-size: 24px;
  justify-content: center;
  align-items: center;
  height: auto;
}

.showIcons {
  transition: transform 0.3s;
  margin-right: 10px;
}

.showIcons1 {
  transform: rotate(-180deg);
}

// 总数盒子
.count_list {
  padding: 0 0 20px;
  display: flex;
  width: 100%;
  justify-content: center;
  align-items: flex-start;
  flex-wrap: wrap;
  // 总数card
  .card_view {
    border: 1px solid #19a97b30;
    border-radius: 0;
    box-shadow: 0px 0px 0px rgba(0, 0, 0, 0.12);
    margin: 0 1% 20px;
    width: 23%;
    box-sizing: border-box;
    height: auto;
    // card头部
    .el-card__header {
      border: 0px solid #19a97b30;
      background: #19a97b10;
      width: 100%;
      border-width: 0 0 1px;
      // 头部盒子
      .index_card_head {
        display: flex;
        width: 100%;
        justify-content: space-between;
        align-items: center;
        height: 10px;
        // 标题
        .card_head_title {
          color: #666;
          font-size: 14px;
        }
        // 按钮盒子
        .card_head_right {
          display: flex;
          align-items: center;
          // 按钮
          .el-icon {
            cursor: pointer;
            color: #aaa;
            font-size: 20px;
          }
        }
      }
    }
    // body
    .el-card__body {
      padding: 0;
      // body盒子
      .count_item {
        padding: 30px;
        text-align: center;
        // 总数标题
        .count_title {
          color: #333;
          font-size: 16px;
          line-height: 2;
        }
        // 总数数字
        .count_num {
          color: #c00;
          font-size: 30px;
          line-height: 2;
        }
      }
    }
  }
}
// 首页盒子
.home_view {
  border: 1px solid #196eab;
  padding: 0px 20px;
  margin: 0 auto 40px;
  overflow: hidden;
  background: url(http://localhost:8080/petshopwx/file/09a98f4852104a599055d47953ef0a23.png)
    no-repeat center top / 100% 100% !important;
  width: calc(100% - 40px) !important;
  min-height: 100vh;
  height: auto;
}
// 统计图盒子
.card_list {
  padding: 0 0 20px;
  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: flex-start;
  flex-wrap: wrap;
  // 统计图card
  .card_view {
    border: 1px solid #19a97b30;
    border-radius: 0;
    box-shadow: 0px 0px 0px rgba(0, 0, 0, 0.12);
    margin: 0 1% 30px;
    width: 48%;
    box-sizing: border-box;
    height: auto;
    // 头部
    .el-card__header {
      border: 0px solid #19a97b30;
      background: #19a97b10;
      width: 100%;
      border-width: 0 0 1px;
      // 头部盒子
      .index_card_head {
        display: flex;
        width: 100%;
        justify-content: space-between;
        align-items: center;
        height: 10px;
        // 标题
        .card_head_title {
          color: #f00;
          font-size: 14px;
        }
        // 按钮盒子
        .card_head_right {
          display: flex;
          align-items: center;
          // 按钮
          .el-icon {
            cursor: pointer;
            color: #aaa;
            font-size: 20px;
          }
        }
      }
    }
    // body
    .el-card__body {
      padding: 0;
      // body盒子
      .card_item {
        padding: 30px;
        text-align: center;
      }
    }
  }
}
</style>
