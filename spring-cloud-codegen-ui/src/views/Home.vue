<template>
	<el-row class="container" style="padding-left: 0px; padding-right: 0px;">
		<el-col :span="24" class="header">
			<el-col :span="10" class="logo" :class="collapsed?'logo-collapse-width':'logo-width'">
				{{collapsed?'':sysName}}
			</el-col>
		</el-col>
		<el-col :span="24" class="main">
			<section class="content-container">
				<div class="grid-content bg-purple-light">
					<el-col :span="24" class="content-wrapper">
						<transition name="fade" mode="out-in">
							<router-view></router-view>
						</transition>
					</el-col>

				</div>
			</section>
		</el-col>
		<el-col :span="24" class="foot">
			<bottomFooter></bottomFooter>
		</el-col>
	</el-row>
</template>

<script>
import bottomFooter from './footer.vue';

export default {
	components: {
		bottomFooter,
	},
	data() {
		return {
			sysName: 'Smart CodeGen',
			collapsed: false,
			sysUserName: '',
			sysUserAvatar: '',
			form: {
				name: '',
				region: '',
				date1: '',
				date2: '',
				delivery: false,
				type: [],
				resource: '',
				desc: ''
			}
		}
	},
	methods: {
		onSubmit() {
			console.log('submit!');
		},
		handleopen() {
			//console.log('handleopen');
		},
		handleclose() {
			//console.log('handleclose');
		},
		handleselect: function(a, b) {
		},
		//退出登录
		logout: function() {
			var _this = this;
			this.$confirm('确认退出吗?', '提示', {
				//type: 'warning'
			}).then(() => {
				sessionStorage.removeItem('user');
				_this.$router.push('/login');
			}).catch(() => {

			});


		},
		//折叠导航栏
		collapse: function() {
			this.collapsed = !this.collapsed;
		},
		showMenu(i, status) {
			this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-' + i)[0].style.display = status ? 'block' : 'none';
		}
	},
	mounted() {
		var user = sessionStorage.getItem('user');
		if (user) {
			user = JSON.parse(user);
			this.sysUserName = user.name || '';
			this.sysUserAvatar = user.avatar || '';
		}

	}
}

</script>

<style scoped lang="scss">
@import '~scss_vars';

.container {
	position: absolute;
	top: 0px;
	bottom: 0px;
	width: 100%;
	.header {
		height: 60px;
		line-height: 60px;
		background-color: #34302d;
		color: #eee;
    border-top: 4px solid #6db33f;
		.userinfo {
			text-align: right;
			padding-right: 35px;
			float: right;
			.userinfo-inner {
				cursor: pointer;
				color: #fff;
				img {
					width: 40px;
					height: 40px;
					border-radius: 20px;
					margin: 10px 0px 10px 10px;
					float: right;
				}
			}
		}
		.logo {
			//width:230px;
			height: 60px;
			font-size: 25px;
			padding-left: 155px;
			padding-right: 20px;
			border-color: rgba(238, 241, 146, 0.3);
			border-right-width: 1px;
			border-right-style: solid;
			img {
				width: 40px;
				float: left;
				margin: 10px 10px 10px 18px;
			}
			.txt {
				color: #fff;
			}
		}
		.logo-width {
			width: 360px;
		}
		.logo-collapse-width {
			width: 60px
		}
		.tools {
			padding: 0px 23px;
			width: 14px;
			height: 60px;
			line-height: 60px;
			cursor: pointer;
		}
	}


	.main {
		// display: flex;
		// background: #324057;
		// position: absolute;
		// top: 60px;
		// bottom: 0px;
		/*overflow: hidden;*/
		// margin-bottom: 300px;
		aside {
			flex: 0 0 230px;
			width: 230px; // position: absolute;
			// top: 0px;
			// bottom: 0px;
			.el-menu {
				height: 100%;
			}
			.collapsed {
				width: 60px;
				.item {
					position: relative;
				}
				.submenu {
					position: absolute;
					top: 0px;
					left: 60px;
					z-index: 99999;
					height: auto;
					display: none;
				}
			}
		}
		.menu-collapsed {
			flex: 0 0 60px;
			width: 60px;
		}
		.menu-expanded {
			flex: 0 0 230px;
			width: 230px;
		}
		.content-container {
			// background: #f1f2f7;
			// flex: 1;
			// position: absolute;
			// right: 0px;
			// top: 0px;
			// bottom: 0px;
			// left: 230px;
			/*overflow-y: scroll*/
			padding: 20px;
			.breadcrumb-container {
				//margin-bottom: 15px;
				.title {
					width: 200px;
					float: left;
					color: #475669;
				}
				.breadcrumb-inner {
					float: right;
				}
			}
			.content-wrapper {
				background-color: #fff;
				box-sizing: border-box;
			}
		}
	}

	.foot {
		display: flex;
		// position: absolute;
		// bottom: 0px;
	}
}
</style>
