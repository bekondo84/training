var header = Vue.component("t-header", {
    props : ["plugins"],
    created() {

    },data() {
       return {
            plugin : null
       }
    },methods :{
        onSelect(plugin) {
           this.plugin = plugin ;
           this.$emit("plugin-select", plugin);
        }
    },
     template : `<nav class="navbar navbar-expand-lg navbar-light bg-light nav-padding" >
                           <div class="container-fluid">
                               <a class="navbar-brand" href="#">
                                   <img src="./images/logo.jpg" width="30" height="30">
                               </a>
                               <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                   <span class="navbar-toggler-icon"></span>
                               </button>
                               <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                   <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                       <li class="nav-item" v-for="plugin of plugins">
                                           <a class="nav-link active" aria-current="page" href="#" v-on:click.prevent.stop="onSelect(plugin)">{{plugin.listTitle}}</a>
                                       </li>
                                   </ul>
                                   <div class="d-flex">
                                      Deconnexion
                                   </div>
                               </div>
                           </div>
                       </nav>`
});