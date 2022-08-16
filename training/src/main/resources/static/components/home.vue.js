var home = Vue.component("t-home", {
   data() {
     return {
           menu : null,
           plugin : null,
           plugins : [],
           menus : []
       }
   },methods : {
      async onPluginChange(plugin) {
          if (this.plugin == null || this.plugin.code != plugin.code ) {
              this.plugin = plugin ;
              this.menus = plugin.menus ;
           }
       }
   },async mounted() {
       try {
          let response = await axios.get("/api/v1/plugins");
          this.plugins = response.data ;
          this.menus = this.plugins[0].menus.slice(0, this.plugins[0].menus.length);
          this.plugin = this.plugins[0];
          if (this.menus != null && this.menus.length > 0) {
              this.menu = this.menus[0];
          }
          //console.log(JSON.stringify(this.menus))
       } catch (error) {
          console.log(err)
       }
   },
   template:`<div class="parent">
                              <t-header :plugins ="plugins" @plugin-select="onPluginChange"/>
                              <main class="container" role="main">
                                  <v-menus-bar :menus ="menus"/>
                              </main>
                            </div>`
});