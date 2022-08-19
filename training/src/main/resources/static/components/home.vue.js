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
           meta : null
       }
   },methods : {
      async onPluginChange(plugin) {
          if (this.plugin == null || this.plugin.code != plugin.code ) {
              this.plugin = plugin ;
              this.menus = plugin.menus ;
           }
       },async onMenuChange(menu) {
           this.menu = menu ;
           if (menu != null) {
              viewModes = menu.viewMode.split(",");
              this.viewMode = viewModes[0];

              if (this.viewMode == 'list') {
                    this.container = menu.listComponent ;
                    let response = await axios.get("/api/v1/meta/".concat(this.menu.metadata));
                    this.meta = response.data ;
                    response = await axios.get(menu.source);
                    this.data = response.data;
              } else {
                 this.container = menu.viewComponent ;
                 let response = await axios.get(menu.source);
                 this.data = response.data;
              }
           }
       },async itemSelected(data) {
         try {
           let response = await axios.get(this.menu.source.concat("/").concat(data.item.item.pk));
           this.data = response.data;
           this.container = this.menu.viewComponent ;
           }catch(error){
             console.log(error);
           }
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
                            @item-selected="itemSelected"></component>
                   </main>
             </div>`
});