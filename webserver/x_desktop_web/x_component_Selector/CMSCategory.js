MWF.xApplication.Selector = MWF.xApplication.Selector || {};
MWF.xDesktop.requireApp("Selector", "Identity", null, false);
MWF.xApplication.Selector.CMSCategory = new Class({
	Extends: MWF.xApplication.Selector.Identity,
    options: {
        "style": "default",
        "count": 0,
        "title": MWF.xApplication.Selector.LP.selectCMSCategory,
        "values": [],
        "names": [],
        "expand": false
    },

    loadSelectItems: function(addToNext){
        this.cmsAction.listCMSApplication(function(json){
            if (json.data.length){
                json.data.each(function(data){
                    data.name = data.appName;
                    var category = this._newItemCategory(data, this, this.itemAreaNode);
                }.bind(this));
            }
        }.bind(this));
    },

    _scrollEvent: function(y){
        return true;
    },
    _getChildrenItemIds: function(){
        return null;
    },
    _newItemCategory: function(data, selector, item, level){
        return new MWF.xApplication.Selector.CMSCategory.ItemCategory(data, selector, item, level)
    },

    _listItemByKey: function(callback, failure, key){
        return false;
    },
    _getItem: function(callback, failure, id, async){
        //this.action.getDepartment(function(json){
        //    if (callback) callback.apply(this, [json]);
        //}.bind(this), failure, id, async);
    },
    _newItemSelected: function(data, selector, item){
        return new MWF.xApplication.Selector.CMSCategory.ItemSelected(data, selector, item)
    },
    _listItemByPinyin: function(callback, failure, key){
        return false;
    },
    _newItem: function(data, selector, container, level){
        return new MWF.xApplication.Selector.CMSCategory.Item(data, selector, container, level);
    }
});
MWF.xApplication.Selector.CMSCategory.Item = new Class({
	Extends: MWF.xApplication.Selector.Identity.Item,
    _getShowName: function(){
        return this.data.name;
    },
    _setIcon: function(){
        this.iconNode.setStyle("background-image", "url("+"/x_component_Selector/$Selector/default/icon/processicon.png)");
    },
    loadSubItem: function(){
        return false;
    //    this.children = new Element("div", {
    //        "styles": this.selector.css.selectorItemCategoryChildrenNode
    //    }).inject(this.node, "after");
    //    this.children.setStyle("display", "block");
    ////    if (!this.selector.options.expand) this.children.setStyle("display", "none");
    //
    //    this.selector.action.listProcess(function(subJson){
    //        subJson.data.each(function(subData){
    //            var category = this.selector._newItem(subData, this.selector, this.children, this.level+1);
    //        }.bind(this));
    //    }.bind(this), null, this.data.id);
    },
    getData: function(callback){
        if (callback) callback();
    },
    checkSelectedSingle: function(){
        var selectedItem = this.selector.options.values.filter(function(item, index){
            if (typeOf(item)==="object") return (this.data.id === item.id) || (this.data.name === item.name) ;
            if (typeOf(item)==="string") return (this.data.id === item) || (this.data.name === item);
            return false;
        }.bind(this));
        if (selectedItem.length){
            this.selectedSingle();
        }
    },
    checkSelected: function(){
        var selectedItem = this.selector.selectedItems.filter(function(item, index){
            return (item.data.id === this.data.id) || (item.data.name === this.data.name);
        }.bind(this));
        if (selectedItem.length){
            //selectedItem[0].item = this;
            selectedItem[0].addItem(this);
            this.selectedItem = selectedItem[0];
            this.setSelected();
        }
    }
});

MWF.xApplication.Selector.CMSCategory.ItemSelected = new Class({
	Extends: MWF.xApplication.Selector.Identity.ItemSelected,
    getData: function(callback){
        if (callback) callback();
    },
    _getShowName: function(){
        return this.data.name;
    },
    _setIcon: function(){
        this.iconNode.setStyle("background-image", "url("+"/x_component_Selector/$Selector/default/icon/processicon.png)");
    }
});

MWF.xApplication.Selector.CMSCategory.ItemCategory = new Class({
    Extends: MWF.xApplication.Selector.Identity.ItemCategory,

    _setIcon: function(){
        this.iconNode.setStyle("background-image", "url("+"/x_component_Selector/$Selector/default/icon/applicationicon.png)");
    },
    loadSub: function(callback){
        if (!this.loaded){

            this.selector.cmsAction.listCMSCategory(function(subJson){
                subJson.data.each(function(subData){
                    subData.name = subData.categoryName;
                    subData.applicationName = this.data.name;
                    subData.application = this.data.id;
                    var category = this.selector._newItem(subData, this.selector, this.children, this.level+1);
                }.bind(this));

                this.loaded = true;
                if (callback) callback();
            }.bind(this), null, this.data.id);
        }else{
            if (callback) callback();
        }
    },
    _hasChild: function(){
        return (this.data.wrapOutCategoryList && this.data.wrapOutCategoryList.length);
    },
    check: function(){}
});
