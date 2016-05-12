(function($scope,$http){var app = angular.module('ranking', [ ]);app.controller('RankingController', function($scope,$http){this.athlete = athletes;});var athletes = [
{"athlete":{"name":"Bond-15","m100Seconds":"11.67","longJumpCentimeters":"473","shotPutMeters":"02.85","highJumpCentimeters":"0.64","m400Seconds":"45.10","m110HurdleSeconds":"13.50","discusThrowMeters":"79.46","poleVaultCentimeters":"5.10","javelinThrowMeters":"100.53","m1500Seconds":"6:50.71"},"m100Score":717,"longJumpScore":332,"shotPutScore":70,"highJumpScore":0,"m400Score":1054,"m110Score":1040,"discusThrowScore":1501,"poleVaultScore":941,"javelinThrowScore":1363,"m1500Score":95,"Day1Score":2173,"Day2Score":4940,"TotalScore":7113}
,{"athlete":{"name":"Burke-10","m100Seconds":"11.01","longJumpCentimeters":"593","shotPutMeters":"22.90","highJumpCentimeters":"2.90","m400Seconds":"69.23","m110HurdleSeconds":"20.07","discusThrowMeters":"22.15","poleVaultCentimeters":"4.59","javelinThrowMeters":"70.40","m1500Seconds":"8:45.70"},"m100Score":858,"longJumpScore":571,"shotPutScore":1281,"highJumpScore":1736,"m400Score":154,"m110Score":344,"discusThrowScore":313,"poleVaultScore":787,"javelinThrowScore":895,"m1500Score":0,"Day1Score":4600,"Day2Score":2339,"TotalScore":6939}
,{"athlete":{"name":"Burks-15","m100Seconds":"16.67","longJumpCentimeters":"771","shotPutMeters":"23.24","highJumpCentimeters":"2.35","m400Seconds":"53.10","m110HurdleSeconds":"15.51","discusThrowMeters":"24.73","poleVaultCentimeters":"3.27","javelinThrowMeters":"88.16","m1500Seconds":"7:49.84"},"m100Score":42,"longJumpScore":987,"shotPutScore":1303,"highJumpScore":1141,"m400Score":677,"m110Score":789,"discusThrowScore":362,"poleVaultScore":423,"javelinThrowScore":1169,"m1500Score":2,"Day1Score":4150,"Day2Score":2745,"TotalScore":6895}
,{"athlete":{"name":"Willis-9","m100Seconds":"12.51","longJumpCentimeters":"876","shotPutMeters":"22.57","highJumpCentimeters":"0.16","m400Seconds":"74.70","m110HurdleSeconds":"15.48","discusThrowMeters":"51.89","poleVaultCentimeters":"5.85","javelinThrowMeters":"33.31","m1500Seconds":"5:18.24"},"m100Score":554,"longJumpScore":1260,"shotPutScore":1261,"highJumpScore":0,"m400Score":56,"m110Score":792,"discusThrowScore":910,"poleVaultScore":1181,"javelinThrowScore":346,"m1500Score":459,"Day1Score":3131,"Day2Score":3688,"TotalScore":6819}
,{"athlete":{"name":"Little-19","m100Seconds":"21.33","longJumpCentimeters":"894","shotPutMeters":"09.60","highJumpCentimeters":"2.76","m400Seconds":"44.24","m110HurdleSeconds":"20.75","discusThrowMeters":"19.28","poleVaultCentimeters":"3.36","javelinThrowMeters":"70.84","m1500Seconds":"7:13.25"},"m100Score":0,"longJumpScore":1309,"shotPutScore":462,"highJumpScore":1578,"m400Score":1099,"m110Score":292,"discusThrowScore":259,"poleVaultScore":446,"javelinThrowScore":902,"m1500Score":46,"Day1Score":4448,"Day2Score":1945,"TotalScore":6393}
,{"athlete":{"name":"Austin-7","m100Seconds":"15.02","longJumpCentimeters":"803","shotPutMeters":"13.27","highJumpCentimeters":"1.78","m400Seconds":"56.44","m110HurdleSeconds":"19.81","discusThrowMeters":"29.54","poleVaultCentimeters":"5.46","javelinThrowMeters":"100.15","m1500Seconds":"8:38.59"},"m100Score":183,"longJumpScore":1068,"shotPutScore":684,"highJumpScore":610,"m400Score":542,"m110Score":364,"discusThrowScore":455,"poleVaultScore":1055,"javelinThrowScore":1357,"m1500Score":0,"Day1Score":3087,"Day2Score":3231,"TotalScore":6318}
,{"athlete":{"name":"Yates-6","m100Seconds":"11.27","longJumpCentimeters":"839","shotPutMeters":"01.87","highJumpCentimeters":"1.59","m400Seconds":"79.67","m110HurdleSeconds":"23.67","discusThrowMeters":"41.24","poleVaultCentimeters":"6.84","javelinThrowMeters":"80.28","m1500Seconds":"5:19.07"},"m100Score":801,"longJumpScore":1162,"shotPutScore":18,"highJumpScore":457,"m400Score":7,"m110Score":118,"discusThrowScore":690,"poleVaultScore":1518,"javelinThrowScore":1047,"m1500Score":455,"Day1Score":2445,"Day2Score":3828,"TotalScore":6273}
,{"athlete":{"name":"Meadows-12","m100Seconds":"15.12","longJumpCentimeters":"534","shotPutMeters":"22.49","highJumpCentimeters":"1.67","m400Seconds":"63.58","m110HurdleSeconds":"17.23","discusThrowMeters":"64.49","poleVaultCentimeters":"4.02","javelinThrowMeters":"69.36","m1500Seconds":"8:24.53"},"m100Score":172,"longJumpScore":449,"shotPutScore":1256,"highJumpScore":520,"m400Score":299,"m110Score":600,"discusThrowScore":1177,"poleVaultScore":623,"javelinThrowScore":880,"m1500Score":0,"Day1Score":2696,"Day2Score":3280,"TotalScore":5976}
,{"athlete":{"name":"Greer-5","m100Seconds":"21.24","longJumpCentimeters":"734","shotPutMeters":"13.71","highJumpCentimeters":"2.71","m400Seconds":"66.17","m110HurdleSeconds":"28.07","discusThrowMeters":"72.90","poleVaultCentimeters":"2.97","javelinThrowMeters":"43.35","m1500Seconds":"5:37.83"},"m100Score":0,"longJumpScore":896,"shotPutScore":711,"highJumpScore":1522,"m400Score":228,"m110Score":1,"discusThrowScore":1358,"poleVaultScore":350,"javelinThrowScore":491,"m1500Score":362,"Day1Score":3357,"Day2Score":2562,"TotalScore":5919}
,{"athlete":{"name":"Adkins-4","m100Seconds":"10.28","longJumpCentimeters":"373","shotPutMeters":"22.96","highJumpCentimeters":"2.75","m400Seconds":"71.39","m110HurdleSeconds":"23.44","discusThrowMeters":"28.40","poleVaultCentimeters":"3.10","javelinThrowMeters":"56.15","m1500Seconds":"7:49.69"},"m100Score":1028,"longJumpScore":164,"shotPutScore":1285,"highJumpScore":1567,"m400Score":110,"m110Score":129,"discusThrowScore":433,"poleVaultScore":381,"javelinThrowScore":680,"m1500Score":2,"Day1Score":4154,"Day2Score":1625,"TotalScore":5779}
,{"athlete":{"name":"Bond-7","m100Seconds":"18.18","longJumpCentimeters":"574","shotPutMeters":"19.34","highJumpCentimeters":"1.05","m400Seconds":"76.41","m110HurdleSeconds":"19.31","discusThrowMeters":"67.33","poleVaultCentimeters":"5.62","javelinThrowMeters":"35.77","m1500Seconds":"4:58.46"},"m100Score":0,"longJumpScore":531,"shotPutScore":1058,"highJumpScore":105,"m400Score":34,"m110Score":406,"discusThrowScore":1237,"poleVaultScore":1106,"javelinThrowScore":381,"m1500Score":569,"Day1Score":1728,"Day2Score":3699,"TotalScore":5427}
,{"athlete":{"name":"Woods-1","m100Seconds":"14.92","longJumpCentimeters":"791","shotPutMeters":"04.21","highJumpCentimeters":"0.63","m400Seconds":"72.43","m110HurdleSeconds":"25.44","discusThrowMeters":"79.61","poleVaultCentimeters":"4.67","javelinThrowMeters":"99.06","m1500Seconds":"6:41.57"},"m100Score":194,"longJumpScore":1038,"shotPutScore":146,"highJumpScore":0,"m400Score":91,"m110Score":49,"discusThrowScore":1504,"poleVaultScore":810,"javelinThrowScore":1340,"m1500Score":120,"Day1Score":1469,"Day2Score":3823,"TotalScore":5292}
,{"athlete":{"name":"Vega-18","m100Seconds":"19.84","longJumpCentimeters":"257","shotPutMeters":"13.37","highJumpCentimeters":"0.12","m400Seconds":"59.34","m110HurdleSeconds":"12.89","discusThrowMeters":"45.59","poleVaultCentimeters":"3.76","javelinThrowMeters":"94.27","m1500Seconds":"7:41.65"},"m100Score":0,"longJumpScore":22,"shotPutScore":690,"highJumpScore":0,"m400Score":436,"m110Score":1123,"discusThrowScore":779,"poleVaultScore":551,"javelinThrowScore":1265,"m1500Score":8,"Day1Score":1148,"Day2Score":3726,"TotalScore":4874}
,{"athlete":{"name":"Martinez-16","m100Seconds":"21.58","longJumpCentimeters":"749","shotPutMeters":"23.92","highJumpCentimeters":"0.45","m400Seconds":"59.50","m110HurdleSeconds":"13.22","discusThrowMeters":"09.24","poleVaultCentimeters":"3.73","javelinThrowMeters":"12.54","m1500Seconds":"6:05.49"},"m100Score":0,"longJumpScore":932,"shotPutScore":1346,"highJumpScore":0,"m400Score":430,"m110Score":1078,"discusThrowScore":79,"poleVaultScore":543,"javelinThrowScore":64,"m1500Score":242,"Day1Score":2708,"Day2Score":2006,"TotalScore":4714}
,{"athlete":{"name":"Cochran-8","m100Seconds":"15.44","longJumpCentimeters":"541","shotPutMeters":"05.88","highJumpCentimeters":"1.47","m400Seconds":"60.56","m110HurdleSeconds":"22.27","discusThrowMeters":"10.90","poleVaultCentimeters":"3.24","javelinThrowMeters":"83.60","m1500Seconds":"3:28.76"},"m100Score":139,"longJumpScore":463,"shotPutScore":242,"highJumpScore":367,"m400Score":394,"m110Score":192,"discusThrowScore":108,"poleVaultScore":416,"javelinThrowScore":1099,"m1500Score":1196,"Day1Score":1605,"Day2Score":3011,"TotalScore":4616}
,{"athlete":{"name":"Gould-7","m100Seconds":"11.60","longJumpCentimeters":"789","shotPutMeters":"10.22","highJumpCentimeters":"0.50","m400Seconds":"71.88","m110HurdleSeconds":"23.72","discusThrowMeters":"32.10","poleVaultCentimeters":"4.23","javelinThrowMeters":"54.32","m1500Seconds":"7:11.24"},"m100Score":732,"longJumpScore":1033,"shotPutScore":499,"highJumpScore":0,"m400Score":101,"m110Score":115,"discusThrowScore":506,"poleVaultScore":682,"javelinThrowScore":653,"m1500Score":50,"Day1Score":2365,"Day2Score":2006,"TotalScore":4371}
,{"athlete":{"name":"Conway-5","m100Seconds":"15.23","longJumpCentimeters":"640","shotPutMeters":"17.94","highJumpCentimeters":"1.18","m400Seconds":"66.23","m110HurdleSeconds":"24.71","discusThrowMeters":"43.11","poleVaultCentimeters":"4.86","javelinThrowMeters":"13.25","m1500Seconds":"6:18.74"},"m100Score":160,"longJumpScore":675,"shotPutScore":971,"highJumpScore":176,"m400Score":226,"m110Score":74,"discusThrowScore":728,"poleVaultScore":868,"javelinThrowScore":73,"m1500Score":193,"Day1Score":2208,"Day2Score":1936,"TotalScore":4144}
,{"athlete":{"name":"Chaney-9","m100Seconds":"19.82","longJumpCentimeters":"597","shotPutMeters":"14.16","highJumpCentimeters":"0.73","m400Seconds":"71.95","m110HurdleSeconds":"16.82","discusThrowMeters":"09.70","poleVaultCentimeters":"4.52","javelinThrowMeters":"80.84","m1500Seconds":"8:32.23"},"m100Score":0,"longJumpScore":580,"shotPutScore":738,"highJumpScore":0,"m400Score":100,"m110Score":643,"discusThrowScore":87,"poleVaultScore":766,"javelinThrowScore":1056,"m1500Score":0,"Day1Score":1418,"Day2Score":2552,"TotalScore":3970}
,{"athlete":{"name":"Ruiz-1","m100Seconds":"15.33","longJumpCentimeters":"357","shotPutMeters":"08.83","highJumpCentimeters":"1.63","m400Seconds":"73.72","m110HurdleSeconds":"19.16","discusThrowMeters":"41.69","poleVaultCentimeters":"2.29","javelinThrowMeters":"32.87","m1500Seconds":"4:59.62"},"m100Score":150,"longJumpScore":140,"shotPutScore":416,"highJumpScore":488,"m400Score":70,"m110Score":419,"discusThrowScore":699,"poleVaultScore":197,"javelinThrowScore":340,"m1500Score":562,"Day1Score":1264,"Day2Score":2217,"TotalScore":3481}
,{"athlete":{"name":"Mejia-6","m100Seconds":"20.02","longJumpCentimeters":"283","shotPutMeters":"12.36","highJumpCentimeters":"1.39","m400Seconds":"52.94","m110HurdleSeconds":"26.04","discusThrowMeters":"20.61","poleVaultCentimeters":"5.14","javelinThrowMeters":"28.11","m1500Seconds":"6:47.34"},"m100Score":0,"longJumpScore":47,"shotPutScore":628,"highJumpScore":310,"m400Score":684,"m110Score":32,"discusThrowScore":284,"poleVaultScore":954,"javelinThrowScore":273,"m1500Score":104,"Day1Score":1669,"Day2Score":1647,"TotalScore":3316}
];})();
