MWF.xApplication.process.FormDesigner.Module = MWF.xApplication.process.FormDesigner.Module || {};
MWF.xDesktop.requireApp("process.FormDesigner", "Module.$Element", null, false);
MWF.xApplication.process.FormDesigner.Module.Subform = MWF.FCSubform = new Class({
	Extends: MWF.FC$Element,
	Implements: [Options, Events],
	options: {
		"style": "default",
		"propertyPath": "/x_component_process_FormDesigner/Module/Subform/subform.html",
        "actions": [
            {
                "name": "move",
                "icon": "move1.png",
                "event": "mousedown",
                "action": "move",
                "title": MWF.APPFD.LP.formAction.move
            },
            {
                "name": "delete",
                "icon": "delete1.png",
                "event": "click",
                "action": "delete",
                "title": MWF.APPFD.LP.formAction["delete"]
            }
            // {
            //     "name": "styleBrush",
            //     "icon": "styleBrush.png",
            //     "event": "click",
            //     "action": "styleBrush",
            //     "title": MWF.APPFD.LP.formAction["styleBrush"]
            // }
        ],
	},
	
	initialize: function(form, options){
		this.setOptions(options);
		
		this.path = "/x_component_process_FormDesigner/Module/Subform/";
		this.cssPath = "/x_component_process_FormDesigner/Module/Subform/"+this.options.style+"/css.wcss";

		this._loadCss();
		this.moduleType = "element";
		this.moduleName = "subform";

		this.form = form;
		this.container = null;
		this.containerNode = null;
	},
	load : function(json, node, parent){

		this.json = json;
		this.node= node;
		this.node.store("module", this);

		//this.node.empty();

		this.node.setStyles(this.css.moduleNode);

		//this._loadNodeStyles();

		this._initModule();

		debugger;
        if (this.json.subformSelected && this.json.subformSelected!=="none" && this.json.subformType!=="script"){
            this.redoSelectedSubform(this.json.subformSelected, null, "");
        }else{
            this.node.empty();
            this.loadIcon();
        }

		this._loadTreeNode(parent);

        this.setCustomStyles();

		this.parentContainer = this.treeNode.parentNode.module;
        this._setEditStyle_custom("id");

		this.parseModules();
        this.json.moduleName = this.moduleName;
        this.node.addEvent("click", function(){
            this.refreshSubform();
        }.bind(this));
        this.node.addEvent("dblclick", function(e){
            this.openSubform(e);
        }.bind(this));
	},
    _initModule: function(){
        if (!this.json.isSaved) this.setStyleTemplate();

        this.setPropertiesOrStyles("styles");
        this.setPropertiesOrStyles("inputStyles");
        this.setPropertiesOrStyles("properties");

        this._setNodeProperty();
        if (!this.form.isSubform) this._createIconAction();
        this._setNodeEvent();
        this.json.isSaved = true;

        this.queryGetFormDataFun = this.queryGetFormData.bind(this);
        this.postGetFormDataFun = this.postGetFormData.bind(this);
        this.form.addEvent("queryGetFormData", this.queryGetFormDataFun);
        this.form.addEvent("postGetFormData", this.postGetFormDataFun);
    },

    openSubform: function(e){
        if (this.json.subformSelected && this.json.subformSelected!=="none" && this.json.subformType!=="script"){
            layout.desktop.openApplication(e, "process.FormDesigner", {"id": this.json.subformSelected, "appId": "FormDesigner"+this.json.subformSelected});
        }
    },
	_createMoveNode: function(){
		this.moveNode = new Element("div", {
			"MWFType": "subform",
			"id": this.json.id,
			"styles": this.css.moduleNodeMove,
			"events": {
				"selectstart": function(){
					return false;
				}
			}
		}).inject(this.form.container);
	},
	_createNode: function(){
		this.node = this.moveNode.clone(true, true);
		this.node.setStyles(this.css.moduleNode);
		this.node.set("id", this.json.id);
		this.node.addEvent("selectstart", function(){
			return false;
		});
        // debugger;
        // if (this.json.subformSelected && this.json.subformSelected!="none" && this.json.subformType!=="script"){
        //     this.redoSelectedSubform(this.json.subformSelected, $(this.property.data.pid+"selectSubform").getElement("select"), "");
        // }else{
             this.loadIcon();
        // }
        this.node.addEvent("click", function(){
            this.refreshSubform();
        }.bind(this));


	},
    postGetFormData: function(node){
        if (!node || node.contains(this.node)) this.show();
    },
    queryGetFormData: function(node){
        debugger;
        if (!node || node.contains(this.node)) this.hide();
    },
    hide: function(){
        this.node.empty();
    },
    show: function(){
        if (this.subformData){
            this.subformModule = new MWF.FCSubform.Form(this.form, this.node);
            this.subformModule.load(this.subformData);
        }else{
            this.node.empty();
            this.loadIcon();
        }
    },
    "delete": function(e){
        var module = this;
        this.form.designer.shortcut = false;
        this.form.designer.confirm("warn", module.node, MWF.APPFD.LP.notice.deleteElementTitle, MWF.APPFD.LP.notice.deleteElement, 300, 120, function(){
            if (this.queryGetFormDataFun) module.form.removeEvent("queryGetFormData", this.queryGetFormDataFun);
            if (this.postGetFormDataFun) module.form.removeEvent("postGetFormData", this.postGetFormDataFun);

            module.destroy();
            module.form.selected();

            module.form.designer.shortcut = true;
            this.close();
        }, function(){
            module.form.designer.shortcut = true;
            this.close();
        }, null);
    },
    refreshSubform: function(){
        if (this.json.subformSelected && this.json.subformSelected!=="none" && this.json.subformType!=="script"){
            MWF.Actions.get("x_processplatform_assemble_designer").getForm(this.json.subformSelected, function(json){
                if (this.subformData.updateTime!==json.data.updateTime){
                    var select = null;
                    if (this.property){
                        select = $(this.property.data.pid+"selectSubform").getElement("select");
                    }
                    this.clearSubformList(this.json.subformSelected);
                    this.reloadSubform(json.data, select, "");
                }
            }.bind(this));
        }
    },
	loadIcon: function(){
        this.iconNode = new Element("div", {
            "styles": this.css.iconNode
        }).inject(this.node);
        new Element("div", {
            "styles": this.css.iconNodeIcon
        }).inject(this.iconNode);
        new Element("div", {
            "styles": this.css.iconNodeText,
            "text": "Subform"
        }).inject(this.iconNode);
	},

	_loadNodeStyles: function(){
		this.iconNode = this.node.getElement("div").setStyles(this.css.iconNode);
		this.iconNode.getFirst("div").setStyles(this.css.iconNodeIcon);
		this.iconNode.getLast("div").setStyles(this.css.iconNodeText);
	},
    _setEditStyle: function(name, input, oldValue){
	    debugger;
		if (name==="subformSelected"){
			if (this.json.subformSelected!==oldValue){
                this.redoSelectedSubform(name, input, oldValue);
			}
		}
        if (name==="subformType"){
		    debugger;
		    if (this.json.subformType!==oldValue){
                if (this.json.subformType !== "script"){
                    this.redoSelectedSubform(name, $(this.property.data.pid+"selectSubform").getElement("select"), "");
                }
                if (this.json.subformType === "script"){
                    this.subformData = null;
                    this.clearSubformList(this.json.subformSelected);
                    this.node.empty();
                    this.loadIcon();
                }
            }
        }
	},
    redoSelectedSubform: function(name, input, oldValue){
        if (this.json.subformSelected==="none") this.json.subformSelected="";
        if (this.json.subformSelected && this.json.subformSelected!=="none"){
            if (this.form.subformList && this.form.subformList[this.json.subformSelected]){
                //var p = (input) ? input.getPosition() : this.node.getPosition();
                var p = this.node.getPosition(document.bosy);
                this.form.designer.alert("error", {
                    "event": {
                        "x": p.x+150,
                        "y": p.y+80
                    }
                }, this.form.designer.lp.subformConflictTitle, this.form.designer.lp.subformConflictInfor, 400, 120);
                this.json.subformSelected = oldValue;
                if (input){
                    for (var i=0; i<input.options.length; i++){
                        if (input.options[i].value===oldValue){
                            input.options[i].set("selected", true);
                            break;
                        }
                    }
                }
                this.node.empty();
                this.loadIcon();
            }else{
                MWF.Actions.get("x_processplatform_assemble_designer").getForm(this.json.subformSelected, function(json){
                    this.reloadSubform(json.data, input, oldValue);
                }.bind(this));
            }
        }else{
            this.subformData = null;
            this.clearSubformList(oldValue);
            this.node.empty();
            this.loadIcon();
        }
    },
    clearSubformList: function(formName){
        if (!this.form.subformList) this.form.subformList = {};
        if (formName) if (this.form.subformList[formName]) delete this.form.subformList[formName];
    },
    addSubformList: function(){
        if (!this.form.subformList) this.form.subformList = {};
        this.form.subformList[this.json.subformSelected] = Object.clone(this.subformData.json);
    },
	getSubformData: function(data){
        var subformDataStr = null;
        if (this.form.options.mode !== "Mobile"){
            subformDataStr = data.data;
        }else{
            subformDataStr = data.mobileData;
        }
        this.subformData = null;
        if (subformDataStr){
            this.subformData = JSON.decode(MWF.decodeJsonString(subformDataStr));
            this.subformData.updateTime = data.updateTime;
        }
	},
	reloadSubform: function(data, input, oldValue){
        this.getSubformData(data);
		if (this.subformData){
		    var oldSubformData = (this.form.subformList && oldValue) ? this.form.subformList[oldValue] : null;
            this.clearSubformList(oldValue);

            if (this.checkSubform(data, input)){
                this.node.empty();
                debugger;
                this.loadSubform();
                this.addSubformList();
            }else{
                if (oldSubformData){
                    if (!this.form.subformList) this.form.subformList = {};
                    this.form.subformList[oldValue] = oldSubformData;
                }else{
                    this.clearSubformList(oldValue);
                    this.node.empty();
                    this.loadIcon();
                }

                this.json.subformSelected = oldValue;
                if (!oldValue){
                    if (input) input.options[0].set("selected", true);
                }else{
                    if (input){
                        for (var i=0; i<input.options.length; i++){
                            if (input.options[i].value===oldValue){
                                input.options[i].set("selected", true);
                                break;
                            }
                        }
                    }
                }
            }
		}else{
            this.json.subformSelected = oldValue;
            if (input){
                if (!oldValue){
                    input.options[0].set("selected", true);
                }else{
                    for (var i=0; i<input.options.length; i++){
                        if (input.options[i].value===oldValue){
                            input.options[i].set("selected", true);
                            break;
                        }
                    }
                }
            }
		}
	},

    regetSubformData: function(){
	    var flag = false;
        if (this.json.subformSelected && this.json.subformSelected!=="none" && this.json.subformType!=="script"){
            MWF.Actions.get("x_processplatform_assemble_designer").getForm(this.json.subformSelected, function(json){
                if (!this.subformData || this.subformData.updateTime!==json.data.updateTime){
                    this.getSubformData(json.data);
                    flag = true;
                }
            }.bind(this), null, false);
        }
        return flag;
    },
    getConflictFields: function(){
        var moduleNames = [];
        if (this.subformData){
            Object.each(this.subformData.json.moduleList, function(o, key){
                var check = this.form.checkModuleId(key, o.type, this.subformData.json.id);
                if (check.fieldConflict){
                    moduleNames.push(key)
                }else if (check.elementConflict){
                    o.changeId = this.json.id+"_"+key;
                }
            }.bind(this));
        }
        return moduleNames;
    },
    checkSubform: function(data, input){
	    var moduleNames = this.getConflictFields();
        // Object.each(this.subformData.json.moduleList, function(o, key){
        //     var check = this.form.checkModuleId(key, o.type, this.subformData.json.id);
        //     if (check.fieldConflict){
        //         moduleNames.push(key)
        //     }else if (check.elementConflict){
        //         o.changeId = this.json.id+"_"+key;
        //     }
        // }.bind(this));
        if (moduleNames.length){
            var txt = this.form.designer.lp.subformNameConflictInfor;
            txt = txt.replace("{name}", moduleNames.join(", "));
            //var p = (input) ? input.getPosition() : this.node.getPosition();
            // var p = this.node.getPosition(document.body);
            // this.form.designer.alert("error", {
            //     "event": {
            //         "x": p.x+150,
            //         "y": p.y+80
            //     }
            // }, this.form.designer.lp.subformNameConflictTitle, txt, 400, 200);

            this.form.designer.notice(txt, "error", this.node);

            return false;
        }
		return true;
    },
	loadSubform: function(data) {
        this.subformData.json.style = this.form.json.style;
        this.subformData.json.properties = this.form.json.properties;
        this.subformData.json.jsheader = {"code": "", "html": ""};
        this.subformData.json.events = {};
        this.subformData.json.formStyleType = this.form.json.formStyleType;
        //this.subformData.json.id = this.json.id;

        this.subformModule = new MWF.FCSubform.Form(this.form, this.node);
        this.subformModule.load(this.subformData);

       //this.createRefreshNode();
    },
    destroy: function(){
        this.form.moduleList.erase(this);
        this.form.moduleNodeList.erase(this.node);
        this.form.moduleElementNodeList.erase(this.node);
        this.clearSubformList(this.json.subformSelected);

        this.node.destroy();
        this.actionArea.destroy();

        delete this.form.json.moduleList[this.json.id];
        this.json = null;
        delete this.json;

        this.treeNode.destroy();
    }
});

MWF.xApplication.process.FormDesigner.Module.Subform.Form = new Class({
    Extends: MWF.FCForm,
    initialize: function(form, container, options){
    	this.parentform = form
        this.css = this.parentform.css;

        this.container = container;
        this.form = this;
        this.isSubform = true;
        this.moduleType = "subform";

        this.moduleList = [];
        this.moduleNodeList = [];

        this.moduleContainerNodeList = [];
        this.moduleElementNodeList = [];
        this.moduleComponentNodeList = [];

        //	this.moduleContainerList = [];
        this.dataTemplate = {};

        this.designer = this.parentform.designer;
        this.selectedModules = [];
    },
    load : function(data){
        this.data = data;
        this.json = data.json;
        this.html = data.html;
        this.json.mode = this.options.mode;

        this.container.set("html", this.html);

        this.loadDomModules();
        //this.setCustomStyles();
        //this.node.setProperties(this.json.properties);
        //this.setNodeEvents();
        if (this.options.mode==="Mobile"){
            if (oldStyleValue) this._setEditStyle("formStyleType", null, oldStyleValue);
        }
    },
    loadDomModules: function(){
        this.node = this.container.getFirst();
        this.node.set("id", this.json.id);
        this.node.setStyles((this.options.mode==="Mobile") ? this.css.formMobileNode : this.css.formNode);
        this.node.store("module", this);
        this.loadDomTree();
    },
    loadDomTree: function(){
        this.createFormTreeNode();
        this.parseModules(this, this.node);
    },
    createFormTreeNode: function(){
        this.treeNode = {
            "insertChild": function(){return this;},
            "appendChild": function(){return this;},
            "selectNode": function(){},
            "node": null,
            "parentNode": {}
        };
        this.treeNode.module = this;
    }
});