(function( $ ) {

	$.extend(Tapestry.Initializer, {
		
		basicComponent : function(spec) {
			
			if(spec.type_graph == "LINE"){
				$("#"+spec.clientId).data('highcharts', {
					chart: {
						renderTo: spec.clientId,
						type: 'line',
						marginRight: 130,
						marginBottom: 25
					},
					title: {
						text: 'CA',
						x: -20 //center
					},
					xAxis: {
						categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
							'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
					},
					yAxis: {
						title: {
							text: 'CA (Euros)'
						},
						plotLines: [{
							value: 0,
							width: 1,
							color: '#808080'
						}]
					},
					tooltip: {
						formatter: function() {
								return '<b>'+ this.series.name +'</b><br/>'+
								this.x +': '+ this.y +' Euros';
						}
					},
					legend: {
						layout: 'vertical',
						align: 'right',
						verticalAlign: 'top',
						x: -10,
						y: 100,
						borderWidth: 0
					}
				});
			}
			else if(spec.type_graph == "BARS"){
				$("#"+spec.clientId).data('highcharts', {
					chart: {
						renderTo: spec.clientId,
						type: 'column'
					},
					title: {
						text: 'CA'
					},
					xAxis: {
						categories: [
							'Jan',
							'Feb',
							'Mar',
							'Apr',
							'May',
							'Jun',
							'Jul',
							'Aug',
							'Sep',
							'Oct',
							'Nov',
							'Dec'
						]
					},
					yAxis: {
						min: 0,
						title: {
							text: 'CA (Euros)'
						}
					},
					legend: {
						layout: 'vertical',
						backgroundColor: '#FFFFFF',
						align: 'left',
						verticalAlign: 'top',
						x: 100,
						y: 70,
						floating: true,
						shadow: true
					},
					tooltip: {
						formatter: function() {
							return ''+
								this.x +': '+ this.y +' Euros';
						}
					},
					plotOptions: {
						column: {
							pointPadding: 0.2,
							borderWidth: 0
						}
					}
				});
			}
			else if(spec.type_graph == "PIE"){
				$("#"+spec.clientId).data('highcharts', {
					chart: {
						renderTo: spec.clientId,
						plotBackgroundColor: null,
						plotBorderWidth: null,
						plotShadow: false
					},
					title: {
						text: 'CA'
					},
					tooltip: {
						formatter: function() {
							return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
						}
					},
					plotOptions: {
						pie: {
							allowPointSelect: true,
							cursor: 'pointer',
							dataLabels: {
								enabled: true,
								color: '#000000',
								connectorColor: '#000000',
								formatter: function() {
									return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
								}
							}
						}
					}
				});
			}
		}
	});
	
}) ( jQuery );