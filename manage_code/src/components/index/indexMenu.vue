<template>
	<div>
		<el-scrollbar wrap-class="scrollbar-wrapper" class="menu_scrollbar">
			<el-menu :default-openeds="[]" :unique-opened="false" default-active="0" class="menu_view"
				:collapse="collapse">
				<el-sub-menu :index="0" @click="menuHandler('')">
					<template #title>
						<i class="iconfont icon-zhuye2" v-if="collapse?true:true"></i>
						<span>首页</span>
					</template>
				</el-sub-menu>
				<template v-for=" (item,index) in menuList.backMenu" :key="item.menu">
					<el-sub-menu :index="index+2+''" v-if="item.child&&item.child.length&&item.child[0].tableName!='orders'">
						<template #title>
							<i class="iconfont" :class="item.fontClass" v-if="collapse?true:true"></i>
							<span>{{ item.menu }}</span>
						</template>
						<el-menu-item class="menu_item_view" v-for=" (child,sort) in item.child" :key="sort"
							:index="(index+2)+'-'+sort"
							@click="menuHandler(child.tableName,child.menuJump)">{{ child.menu }}
						</el-menu-item>
					</el-sub-menu>
				</template>
				<el-sub-menu :index="100" @click="menuHandler('orders')" v-if="btnAuth('orders','查看')">
					<template #title>
						<i class="iconfont" :class="orderIcon" v-if="collapse?true:true"></i>
						<span>商品订单</span>
					</template>
				</el-sub-menu>
			</el-menu>
		</el-scrollbar>
	</div>
</template>

<script setup>
	import menu from '@/utils/menu'
	import {
		ref,
		toRefs,
		getCurrentInstance,
		nextTick
	} from 'vue';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	//props
	const props = defineProps({
		collapse: Boolean
	})
	const {
		collapse
	} = toRefs(props)
	//data
	const menuList = ref([])
	const role = ref('')
	const styleChange = () => {
		nextTick(() => {
			document.querySelectorAll('.el-menu-vertical-demo .el-sub-menu .el-menu').forEach(el => {
				el.removeAttribute('style')
				const icon = {
					"border": "none",
					"padding": "0",
					"margin": "10px auto 0",
					"borderRadius": "0px",
					"background": "none",
					"display": "none",
					"width": "100%"
				}
				Object.keys(icon).forEach((key) => {
					el.style[key] = icon[key]
				})
			})
		})
	}
	const orderIcon = ref('')
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	const init = () => {
		const menus = menu.list()
		if (menus) {
			menuList.value = menus
		}
		role.value = context?.$toolUtil.storageGet('role')

		for (let i = 0; i < menuList.value.length; i++) {
			if (menuList.value[i].roleName == role.value) {
				menuList.value = menuList.value[i];
				break;
			}
		}
		for(let x in menuList.value.backMenu){
			if(menuList.value.backMenu[x].child&&menuList.value.backMenu[x].child.length&&menuList.value.backMenu[x].child[0].tableName=='orders'){
				orderIcon.value = menuList.value.backMenu[x].fontClass?menuList.value.backMenu[x].fontClass:''
				break
			}
		}
		// styleChange()
	}
	const menuHandler = (name,menuJump) => {
		if(name == 'center'){
			name = `${role.value}Center`
		}
		if(name == 'storeup'){
			name = `storeup?type=${menuJump}`
		}
		if(name == 'exampaper' && menuJump == '12'){
			name = 'exampaperlist'
		}
		if(name == 'examrecord' && menuJump == '22'){
			name = 'examfailrecord'
		}
		let router = context?.$router
		name = '/' + name
		router.push(name)
	}
	init()
</script>

<style lang="scss" scoped>
	// 总盒子
	:deep(.menu_scrollbar) {

		// 菜单盒子-展开样式
		.menu_view {
			border: 0;
			padding: 0 0 60px;
			color: #fff;
			background: none;
			height: 100%;

			// 无二级菜单
			.el-menu-item {
				padding: 0 10px;
				margin: 0 0 10px;
				clip-path: polygon(96% 0, 100% 50%, 96% 100%, 0% 100%, 0% 0%);
				color: #fff;
				background: linear-gradient(270deg, rgba(130,196,209,1) 0%, rgba(115,186,200,1) 24%, rgba(174,210,217,1) 100%);
				border-color: #196eab30;
				border-width: 1px 0;
				line-height: 40px;
				border-style: solid;
				height: 40px;
				.iconfont {
					margin: 0 0px 0 0;
					color: inherit;
					width: 24px;
					vertical-align: middle;
					font-size: 20px;
					text-align: center;
				}
			}

			// 无二级悬浮
			.el-menu-item:hover {
				padding: 0 10px;
				margin: 0 0 10px;
				clip-path: polygon(96% 0, 100% 50%, 96% 100%, 0% 100%, 0% 0%);
				color: #fff;
				background: linear-gradient(30deg, rgba(130,196,209,1) 0%, rgba(115,186,200,1) 24%, rgba(174,210,217,1) 100%);
				border-color: #196eab30;
				border-width: 1px 0;
				line-height: 40px;
				border-style: solid;
				height: 40px;
			}

			// 无二级选中
			.el-menu-item.is-active {
				padding: 0 10px;
				margin: 0 0 10px;
				clip-path: polygon(96% 0, 100% 50%, 96% 100%, 0% 100%, 0% 0%);
				color: #fff;
				background: linear-gradient(30deg, rgba(130,196,209,1) 0%, rgba(115,186,200,1) 24%, rgba(174,210,217,1) 100%);
				border-color: #196eab30;
				border-width: 1px 0;
				line-height: 40px;
				border-style: solid;
				height: 40px;
			}

			// 有二级盒子
			.el-sub-menu {
				cursor: pointer;
				padding: 0 0;
				color: #333;
				white-space: nowrap;
				background: none;
				position: relative;

				// 有二级item
				.el-sub-menu__title {
					padding: 0 10px;
					margin: 0 0 10px;
					clip-path: polygon(96% 0, 100% 50%, 96% 100%, 0% 100%, 0% 0%);
					color: #fff;
					background: linear-gradient(270deg, rgba(130,196,209,1) 0%, rgba(115,186,200,1) 24%, rgba(174,210,217,1) 100%);
					border-color: #196eab30;
					border-width: 1px 0;
					line-height: 40px;
					border-style: solid;
					height: 40px;
					.iconfont {
						margin: 0 0px 0 0;
						color: inherit;
						width: 24px;
						vertical-align: middle;
						font-size: 20px;
						text-align: center;
					}
					.el-sub-menu__icon-arrow{
						margin: -3px 0 0 8px;
						color: inherit;
						vertical-align: middle;
						font-size: 12px;
						position: static;
					}
				}

				// 有二级item悬浮
				.el-sub-menu__title:hover {
					padding: 0 10px;
					margin: 0 0 10px;
					clip-path: polygon(96% 0, 100% 50%, 96% 100%, 0% 100%, 0% 0%);
					color: #fff;
					background: linear-gradient(30deg, rgba(130,196,209,1) 0%, rgba(115,186,200,1) 24%, rgba(174,210,217,1) 100%);
					border-color: #196eab30;
					border-width: 1px 0;
					line-height: 40px;
					border-style: solid;
					height: 40px;
				}
			}
			//二级选中
			.is-active {
				.el-sub-menu__title {
					padding: 0 10px;
					margin: 0 0 10px;
					clip-path: polygon(96% 0, 100% 50%, 96% 100%, 0% 100%, 0% 0%);
					color: #fff;
					background: linear-gradient(30deg, rgba(130,196,209,1) 0%, rgba(115,186,200,1) 24%, rgba(174,210,217,1) 100%);
					border-color: #196eab30;
					border-width: 1px 0;
					line-height: 40px;
					border-style: solid;
					height: 40px;
				}
			}
			// 二级盒子
			.el-menu--inline {
				border: none;
				padding: 0px;
				background: none;
				// 二级菜单
				.menu_item_view {
					padding: 0 20px 0 52px;
					margin: 0 0 10px;
					color: #666;
					background: none;
					border-color: #ddd;
					border-width: 0 0 0px;
					line-height: 40px;
					border-style: solid;
					height: 40px;
				}
				// 二级悬浮
				.menu_item_view:hover {
					padding: 0 20px 0 52px;
					margin: 0 0 10px;
					clip-path: polygon(96% 0, 100% 50%, 96% 100%, 0% 100%, 0% 0%);
					color: #fff;
					background: linear-gradient(30deg, rgba(130,196,209,1) 0%, rgba(115,186,200,1) 24%, rgba(174,210,217,1) 100%);
					border-color: #19a97b20;
					border-width: 0 0 0px;
					line-height: 40px;
					border-style: solid;
					height: 40px;
				}
				// 二级选中
				.is-active.menu_item_view {
					padding: 0 20px 0 52px;
					margin: 0 0 10px;
					clip-path: polygon(96% 0, 100% 50%, 96% 100%, 0% 100%, 0% 0%);
					color: #fff;
					background: linear-gradient(30deg, rgba(130,196,209,1) 0%, rgba(115,186,200,1) 24%, rgba(174,210,217,1) 100%);
					border-color: #19a97b20;
					border-width: 0 0 0px;
					line-height: 40px;
					border-style: solid;
					height: 40px;
				}
			}
		}
		// 菜单盒子-关闭样式
		.el-menu--collapse {
			padding: 0 10px;
			color: #fff;
			background: red;
			height: 100%;

			// 无二级菜单
			.el-menu-item {
				padding: 0 10px;
				color: #666;
				background: #fff;
				line-height: 50px;
				height: 50px;
				.iconfont {
					margin: 0 5px 0 0;
					color: inherit;
					width: 34px;
					vertical-align: middle;
					font-size: 20px;
					text-align: center;
				}
			}

			// 无二级悬浮
			.el-menu-item:hover {
				padding: 0 10px;
				color: #ccc;
				background: gray;
				line-height: 50px;
				height: 50px;
			}

			// 无二级选中
			.el-menu-item.is-active {
				padding: 0 10px;
				color: #ccc;
				background: #123;
				line-height: 50px;
				height: 50px;
			}

			// 有二级盒子
			.el-sub-menu {
				cursor: pointer;
				padding: 0 0;
				color: #333;
				white-space: nowrap;
				background: #fff;
				position: relative;

				// 有二级item
				.el-sub-menu__title {
					padding: 0 10px;
					color: #666;
					background: #fff;
					line-height: 50px;
					height: 50px;
					.iconfont {
						margin: 0 5px 0 0;
						color: inherit;
						width: 34px;
						vertical-align: middle;
						font-size: 20px;
						text-align: center;
					}
					.el-sub-menu__icon-arrow{
						margin: -3px 0 0 8px;
						color: inherit;
						vertical-align: middle;
						font-size: 12px;
						position: static;
					}
				}

				// 有二级item悬浮
				.el-sub-menu__title:hover {
					padding: 0 10px;
					color: #ccc;
					background: gray;
					line-height: 50px;
					height: 50px;
				}
			}
			//二级选中
			.is-active {
				.el-sub-menu__title {
					padding: 0 10px;
					color: #ccc;
					background: #123;
					line-height: 50px;
					height: 50px;
				}
			}
			// 二级盒子
			.el-menu--inline {
				border: none;
				padding: 4px;
				background: #123;
				// 二级菜单
				.menu_item_view {
					padding: 0 40px;
					color: #666;
					background: goldenrod;
					line-height: 50px;
					height: 50px;
				}
				// 二级悬浮
				.menu_item_view:hover {
					padding: 0 40px;
					color: #f00;
					background: greenyellow;
					line-height: 50px;
					height: 50px;
				}
				// 二级选中
				.is-active.menu_item_view {
					padding: 0 40px;
					color: #000;
					background: burlywood;
					line-height: 50px;
					height: 50px;
				}
			}
		}
	}
</style>
<style lang="scss">
	.el-popper{
		.el-menu--popup-container {
			.el-menu--popup{
				border: none;
				padding: 4px;
				background: #123;
				// 二级菜单
				.menu_item_view {
					padding: 0 40px;
					color: #666;
					background: goldenrod;
					line-height: 50px;
					height: 50px;
				}
				// 二级悬浮
				.menu_item_view:hover {
					padding: 0 40px;
					color: #f00;
					background: greenyellow;
					line-height: 50px;
					height: 50px;
				}
				// 二级选中
				.is-active.menu_item_view {
					padding: 0 40px;
					color: #000;
					background: burlywood;
					line-height: 50px;
					height: 50px;
				}
			}
		}
	}
</style>
