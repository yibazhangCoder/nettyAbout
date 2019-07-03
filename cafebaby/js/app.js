window.app={
	
	/**
	 * 判断字符是否为空
	 */
	
	isNotNull:function(str){
		
		if(str !=null && str !='' && str!=undefined){
			return true;
		}
		return false;
	},
	/**
	 * 弹框显示
	 * @param {Object} msg
	 * @param {Object} type
	 */
	showToast:function(msg,type){
		plus.nativeUI.toast(msg,{icon:"/images/icon-"+type+".png",verticalAlign:"center"})
	},
	/**
	 * 全局url
	 */
	appGloableUrl:"http://172.20.10.11:8080",
	
	/**
	 *保存全局用户信息 
	 */
	saveGlobeUserInfo:function(user){
		var userInfo = JSON.stringify(user);
		plus.storage.setItem("userInfo",userInfo);
	},
	
	/**
	 * 获取全局用户信息
	 */
	getGlobeUserInfo:function(){
		return JSON.parse(plus.storage.getItem("userInfo"));
	},
	loginOut:function(){
		plus.storage.removeItem("userInfo");
	}
}
