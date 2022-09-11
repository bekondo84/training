var home = Vue.component("t-home", {
   data() {
     return {
           menu : null,
           plugin : null,
           plugins : [],
           menus : [],
           container : null,
           viewMode : null,
           data : null,
           meta : null,
           caretaker : []
       }
   },methods : {
      async onPluginChange(plugin) {
          if (this.plugin == null || this.plugin.code != plugin.code ) {
              this.plugin = plugin ;
              this.menus = plugin.menus ;
              this.caretaker = [];
           }
       },async onMenuChange(menu) {
           this.menu = menu ;
           this.caretaker = [];
           if (menu != null) {
              viewModes = menu.viewMode.split(",");
              this.viewMode = viewModes[0];
               let response = await axios.get("/api/v1/meta/".concat(this.menu.metadata));
               this.meta = response.data ;
              if (this.viewMode == 'list') {
                    this.container = menu.listComponent ;
                    response = await axios.get(menu.source);
                    this.data = response.data;
              } else {
                 this.container = menu.viewComponent ;
                 response = await axios.get(menu.source);
                 this.data = response.data;
              }
           }
       }, createdMomento() {
           let momento = new Object();
           momento.menu = this.menu ;
           momento.data = this.data ;
           momento.container = this.container;
           momento.meta = this.meta;
           momento.viewMode = this.viewMode;
           this.caretaker.push(momento);
       }, restoreMomento() {
          let momento = this.caretaker.pop();
          this.menu = momento.menu;
          this.data = momento.data;
          this.container = momento.container;
          this.meta = momento.meta;
          this.viewMode = momento.viewMode;
       },async itemSelected(data) {
         try {
             this.createdMomento();
             let response = await axios.get(this.menu.source.concat("/").concat(data.item.item.pk));
             this.data = response.data;
             this.container = this.menu.viewComponent ;
             this.viewMode = "view";
           }catch(error){
             console.log(error);
           }
       },async createdAction(data) {
          this.createdMomento();
          this.data = data;
          this.container = this.menu.viewComponent ;
          this.viewMode = "view";
       },async onCancelEvent() {
           this.restoreMomento();
           let source = this.menu.source;
           if (!Array.isArray(this.data)) {
               source = source.concat("/").concat(this.data.pk);
           }
           let response = await axios.get(source);
           this.data = response.data;
        },async processAction(obj) {
           try {
             this.createdMomento();
             let item = obj.data ;
             let action = obj.action;
             let response = await axios.get("/api/v1/meta/".concat(action.metadata));
             this.meta = response.data ;
             action.source = action.source.concat("/").concat(item.pk);
             response = await axios.get(action.source);
             this.data = response.data ;
             this.menu = action ;
             let viewModes = action.scope.split(",");
             this.viewMode = viewModes[0];
             if (this.viewMode == 'list') {
                  this.container = action.listComponent;
             } else {
                 this.container = action.viewComponent;
             }
           } catch (error) {
              console.log(error);
           }
        }
   },computed : {
        keyValue() {
            return this.menu.name;
        },activatedBackButton() {
            return this.caretaker.length > 1;
        }
   },async mounted() {
       try {
          let response = await axios.get("/api/v1/plugins");
          this.plugins = response.data ;
          this.menus = this.plugins[0].menus.slice(0, this.plugins[0].menus.length);
          this.plugin = this.plugins[0];
          if (this.menus != null && this.menus.length > 0) {
             // this.menu = this.menus[0];
             // this.container = this.menus[0].component;
          }
          //console.log(JSON.stringify(this.menus))
       } catch (error) {
          console.log(err)
       }
   },
   template:`<div class="parent">
                   <t-header :plugins ="plugins" @plugin-select="onPluginChange" />
                   <main class="container" role="main">
                       <v-menus-bar :menus ="menus" @menu-change="onMenuChange"/>
                       <component v-bind:is="container"
                            :menu="menu"
                            :data="data"
                            :meta="meta"
                            :backbtn="activatedBackButton"
                            @item-selected="itemSelected"
                            @created-action="createdAction"
                            @cancel-event="onCancelEvent"
                            @refresh-list-form="onCancelEvent"
                            @process-action="processAction"></component>
                   </main>
             </div>`
});