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


