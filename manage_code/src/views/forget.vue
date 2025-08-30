<template>
  <div>
    <div class="forget_view">
      <el-form :model="forgetForm" class="forget_box">
        <div class="forget_title">
          宠物店小程序{{
            pageType == 1 ? '找回密码' : pageType == 2 ? '输入密保' : '重置密码'
          }}
        </div>
        <div class="tab_line">
          <div class="line"></div>
          <div class="num_line">
            <div
              class="line_number"
              :class="
                (pageType == 1 ? 'line_number1' : '',
                pageType > 1 ? 'line_number2' : '')
              "
            >
              <div class="number" v-if="pageType < 2">1</div>
              <el-icon v-else><Check /></el-icon>
            </div>
            <div
              class="line_number"
              :class="
                (pageType == 2 ? 'line_number1' : '',
                pageType > 2 ? 'line_number2' : '')
              "
            >
              <div class="number" v-if="pageType < 3">2</div>
              <el-icon v-else><Check /></el-icon>
            </div>
            <div
              class="line_number"
              :class="pageType == 3 ? 'line_number1' : ''"
            >
              <div class="number">3</div>
            </div>
          </div>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick } from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
const pageType = ref(1)
const forgetForm = ref({})
const userForm = ref({})
//返回登录
const close = () => {
  context?.$router.push({
    path: '/login',
  })
}
</script>

<style lang="scss" scoped>
.forget_view {
  background-repeat: no-repeat;
  flex-direction: column;
  background-size: 100% 100% !important;
  background: url(http://localhost:8080/petshopwx/file/cb59505e774a42899501d8d7f1360b75.jpg);
  display: flex;
  min-height: 100vh;
  justify-content: center;
  align-items: center;
  position: relative;
  background-position: center center;
  // 表单盒子
  .forget_box {
    border-radius: 0px;
    padding: 50px 80px 30px 80px;
    margin: 0 auto;
    background: url(http://localhost:8080/petshopwx/file/7ac2edfec9b84ae5be0a62f62e8af7bb.png)
        no-repeat center top / 100% auto,
      #f7f2ec;
    display: flex;
    width: 600px;
    justify-content: flex-start;
    flex-wrap: wrap;
    // 标题
    .forget_title {
      padding: 0px;
      margin: 0 auto 20px;
      color: #333;
      font-weight: 500;
      width: 80%;
      font-size: 23px;
      text-align: center;
    }
    // tab
    // 盒子
    .tab_line {
      padding: 30px 0;
      flex-direction: column;
      display: flex;
      width: 100%;
      align-items: center;
      // 中间线
      .line {
        background: #ccc;
        width: 80%;
        height: 3px;
      }
      // item盒子
      .num_line {
        margin: -20px 0 0;
        display: flex;
        width: 80%;
        justify-content: space-between;
        align-items: center;
        height: 40px;
        // 默认样式
        .line_number {
          border: 4px solid #ddd;
          border-radius: 50%;
          color: #aaa;
          background: #eee;
          display: flex;
          width: 40px;
          font-size: 18px;
          justify-content: center;
          align-items: center;
          height: 40px;
        }
        // 选中样式
        .line_number1 {
          color: #39c8c9;
          font-size: 22px;
          border-color: #39c8c9;
        }
        // 完成样式
        .line_number2 {
          color: #19a97b;
          border-color: #19a97b;
        }
      }
    }
    // item
    .list_item {
      margin: 10px auto;
      display: flex;
      width: 100%;
      justify-content: flex-start;
      align-items: center;
      // label
      .item_label {
        background: none;
        display: block;
        width: 130px;
        font-size: 14px;
        line-height: 36px;
        box-sizing: border-box;
        text-align: right;
        height: 36px;
      }
      // 输入框
      :deep(.list_inp) {
        border: 1px solid #ddd;
        border-radius: 0px;
        padding: 0 10px;
        color: #666;
        background: #fff;
        width: 100%;
        line-height: 36px;
        box-sizing: border-box;
        height: 36px;
        //去掉默认样式
        .el-input__wrapper {
          border: none;
          box-shadow: none;
          background: none;
          border-radius: 0;
          height: 100%;
          padding: 0;
        }
        .is-focus {
          box-shadow: none !important;
        }
      }
      //下拉框样式
      :deep(.list_sel) {
        border: 1px solid #ddd;
        border-radius: 0px;
        padding: 0 10px;
        color: #666;
        background: #fff;
        width: 100%;
        line-height: 36px;
        box-sizing: border-box;
        //去掉默认样式
        .select-trigger {
          height: 100%;
          .el-input {
            height: 100%;
            .el-input__wrapper {
              border: none;
              box-shadow: none;
              background: none;
              border-radius: 0;
              height: 100%;
              padding: 0;
            }
            .is-focus {
              box-shadow: none !important;
            }
          }
        }
      }
    }
    // 按钮盒子
    .list_btn {
      margin: 20px auto 0;
      display: flex;
      width: 80%;
      justify-content: center;
      align-items: center;
      flex-wrap: wrap;
      // 获取密保
      :deep(.el-button--success) {
        border: 0;
        cursor: pointer;
        border-radius: 0px;
        padding: 0 40px;
        margin: 0 10px 0 0;
        color: #fff;
        background: linear-gradient(
          270deg,
          rgba(246, 154, 40, 1) 0%,
          rgba(255, 186, 101, 1) 50%,
          rgba(246, 154, 40, 1) 100%
        );
        width: auto;
        font-size: 14px;
        height: 36px;
      }
      // 获取密保悬浮
      :deep(.el-button--success:hover) {
      }
      // 确认密保
      :deep(.el-button--primary) {
        border: 0;
        cursor: pointer;
        border-radius: 0px;
        padding: 0 40px;
        margin: 0 10px 0 0;
        outline: none;
        color: #fff;
        background: linear-gradient(
          270deg,
          rgba(246, 154, 40, 1) 0%,
          rgba(255, 186, 101, 1) 50%,
          rgba(246, 154, 40, 1) 100%
        );
        width: auto;
        font-size: 14px;
        height: 36px;
      }
      // 确认密保悬浮
      :deep(.el-button--primary:hover) {
      }
      // 重置密码
      :deep(.el-button--warning) {
        border: 0;
        cursor: pointer;
        border-radius: 0px;
        padding: 0 40px;
        margin: 0 10px 0 0;
        outline: none;
        color: #fff;
        background: linear-gradient(
          270deg,
          rgba(246, 154, 40, 1) 0%,
          rgba(255, 186, 101, 1) 50%,
          rgba(246, 154, 40, 1) 100%
        );
        width: auto;
        font-size: 14px;
        height: 36px;
      }
      // 重置密码悬浮
      :deep(.el-button--warning:hover) {
      }
      .r-login {
        cursor: pointer;
        padding: 10px 0 0;
        color: #999;
        width: 100%;
        font-size: 14px;
        text-align: right;
      }
    }
  }
}
</style>
