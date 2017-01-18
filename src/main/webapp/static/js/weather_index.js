var china = echarts.init(document.getElementById('china_map'));
china.setOption({
	series: [{
		type: 'map',
        map: 'china',
        roam: true,
        label: {
        	normal: {show: true},
        	emphasis: {show: true}
        },
        data:[
        	{name: '北京', value: 'beijing'},
        	{name: '天津', value: 'tianjin'},
        	{name: '上海', value: 'shanghai'},
        	{name: '重庆', value: 'chongqing'},
        	{name: '河北', value: 'hebei'},
        	{name: '河南', value: 'henan'},
        	{name: '云南', value: 'yunnan'},
        	{name: '辽宁', value: 'liaoning'},
        	{name: '黑龙江', value: 'heilongjiang'},
        	{name: '湖南', value: 'hunan'},
        	{name: '安徽', value: 'anhui'},
        	{name: '山东', value: 'shandong'},
        	{name: '新疆', value: 'xinjiang'},
        	{name: '江苏', value: 'jiangsu'},
        	{name: '浙江', value: 'zhejiang'},
        	{name: '江西', value: 'jiangxi'},
        	{name: '湖北', value: 'hubei'},
        	{name: '广西', value: 'guangxi'},
        	{name: '甘肃', value: 'gansu'},
        	{name: '山西', value: 'shanxi'},
        	{name: '内蒙古', value: 'neimenggu'},
        	{name: '陕西', value: 'shan-xi'},
        	{name: '吉林', value: 'jilin'},
        	{name: '福建', value: 'fujian'},
        	{name: '贵州', value: 'guizhou'},
        	{name: '广东', value: 'guangdong'},
        	{name: '青海', value: 'qinghai'},
        	{name: '西藏', value: 'xizang'},
        	{name: '四川', value: 'sichuan'},
        	{name: '宁夏', value: 'ningxia'},
        	{name: '海南', value: 'hainan'},
        	{name: '台湾', value: 'taiwan'},
        	{name: '香港', value: 'xianggang'},
        	{name: '澳门', value: 'aomen'}
        ]
    }]
});

china.on('click', function (params) {
	window.location.href="/PersonTool/f/weather/basicCity/prov?provC=" + params.name + "&provE=" + params.data.value; 
});