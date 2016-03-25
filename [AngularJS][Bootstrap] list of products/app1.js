(function(){
	var app = angular.module('store', [ ]);
	
	app.controller('StoreController', function(){
		this.product = products;
	});
	
	app.controller("PanelController", function(){
		this.tab = 1; 
		
		this.selectTab = function(setTab) {
			this.tab = setTab;
		};
		
		this.isSelected = function(checkTab){
			return this.tab === checkTab;
		};
	});
	
	app.controller("CartController", function($scope){
		$scope.cart = [
			{text: 'Fedora', quantity:1},      
    		{text: 'Arch Linux', quantity:2}
		]; 
		
		$scope.getTotal= function () {
			var total = 0;
			for (var i = $scope.cart.length - 1; i > -1; i--) {
				total += $scope.cart[i].quantity;
			}		
    		return total;
  		};
  		
  		$scope.addElement= function (element) {
  			var found = false;
  			for (var i = $scope.cart.length - 1; i > -1; i--) {
				if ($scope.cart[i].text === element.text){
					$scope.cart[i].quantity += 1;
					found = true;
					return;
				}	
			};
			if (found == false){
				$scope.cart.push({text:element.text, quantity:1});
			}	  			
  		};
  		
  		$scope.removeElement= function (element) {
  			console.log(element);
  			for (var i = $scope.cart.length - 1; i > -1; i--) {
				if ($scope.cart[i].text == element.text){
					return $scope.cart.splice(i,1);					
				}	
			};		
  		};
  		
  		$scope.removeAll= function () {
			$scope.cart = [];
  		};
	});
	
	var products = [
 		{
			name: "Linux Mint",
			origin: "Ireland",
			hits: 3111,
			description: "Linux Mint is an Ubuntu-based distribution whose goal is to provide a more complete out-of-the-box experience by including browser plugins, media codecs, support for DVD playback, Java and other components. It also adds a custom desktop and menus, several unique configuration tools, and a web-based package installation interface. Linux Mint is compatible with Ubuntu software repositories. ",
			images: [
				{
					full: './logos/mint.png',
					thumb: './logos/mint.png'
				},
				{
					full: './logos/mint.png',
					thumb: './logos/mint.png'
				}
			],	
			architecture: "i386, x86_64",
			desktop: "Cinnamon, GNOME, KDE, MATE, Xfce",
			category: "Beginners, Desktop, Live Medium",
			canPurchase: true,
		},
		{
			name: "Ubuntu",
			origin: "Isle of Man",
			hits: 1638,
			description: "Ubuntu is a complete desktop Linux operating system, freely available with both community and professional support. The Ubuntu community is built on the ideas enshrined in the Ubuntu Manifesto: that software should be available free of charge, that software tools should be usable by people in their local language and despite any disabilities, and that people should have the freedom to customise and alter their software in whatever way they see fit. ",
			images: [
				{
					full: './logos/ubuntu.png',
					thumb: './logos/ubuntu.jpg'
				},
				{
					full: './logos/ubuntu.jpg',
					thumb: './logos/ubuntu.png'
				}
			],
			architecture: "i386, x86_64",
			desktop: "Cinnamon, GNOME, KDE, MATE, Xfce",
			category: "Beginners, Desktop, Live Medium",
			canPurchase: true,
		},
		{
			name: "OpenSUSE",
			origin: "Germany",
			hits: 1495,
			description: "The openSUSE project is a community program sponsored by SUSE Linux and other companies. Promoting the use of Linux everywhere, this program provides free, easy access to openSUSE, a complete Linux distribution. The openSUSE project has three main goals: make openSUSE the easiest Linux for anyone to obtain and the most widely used Linux distribution; leverage open source collaboration to make openSUSE the world's most usable Linux distribution and desktop environment for new and experienced Linux users; dramatically simplify and open the development and packaging processes to make openSUSE the platform of choice for Linux developers and software vendors. ",
			images: [
				{
					full: './logos/opensuse.png',
					thumb: './logos/opensuse.jpg'
				},
				{
					full: './logos/opensuse.jpg',
					thumb: './logos/opensuse.png'
				}
			],
			architecture: "armhf, i586, x86_64",
			desktop: "Cinnamon, GNOME, IceWM, KDE, LXDE, Openbox, WMaker, Xfce",
			category: "Desktop, Server, Live Medium, Raspberry Pi",
			canPurchase: true,
		},
		{
			name: "Fedora",
			origin: "USA",
			hits: 1178,
			description: "Fedora (formerly Fedora Core) is a Linux distribution developed by the community-supported Fedora Project and owned by Red Hat. Fedora contains software distributed under a free and open-source license and aims to be on the leading edge of such technologies. Fedora has a reputation for focusing on innovation, integrating new technologies early on and working closely with upstream Linux communities. The default desktop in Fedora is the GNOME desktop environment and the default interface is the GNOME Shell.",
			images: [
				{
					full: './logos/fedora.png',
					thumb: './logos/fedora.png'
				},
				{
					full: './logos/fedora.png',
					thumb: './logos/fedora.png'
				}
			],
			architecture: "i386, x86_64",
			desktop: "Cinnamon, GNOME, KDE, MATE, Xfce",
			category: "Beginners, Desktop, Live Medium",
			canPurchase: true,
		}
	];
})();


