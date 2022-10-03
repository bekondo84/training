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
        },changePassord() {
            this.$emit("password-change-action");
        }, lockout() {
            this.$emit("lockout-action");
        }
    },
     template : `<nav class="navbar navbar-expand-lg navbar-dark bg-dark nav-padding" style="background-color: #3c8dbc;">
                           <div class="container-fluid">
                               <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                   <span class="navbar-toggler-icon"></span>
                               </button>
                               <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                   <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                       <li class="nav-item" v-for="plugin of plugins">
                                           <a class="nav-link active" aria-current="page" href="#" v-on:click.prevent.stop="onSelect(plugin)">{{plugin.code}}</a>
                                       </li>
                                   </ul>
                                   <div class="d-flex">
                                      <ul class="nav nav-pills">
                                         <li class="nav-item dropstart">
                                          <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">
                                             <img src="./images/user.png" width="20" height="20">
                                          </a>
                                          <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" href="#" @click="changePassord">Changer votre mot de passe</a></li>
                                            <li><hr class="dropdown-divider"></li>
                                            <li><a class="dropdown-item" href="/logout">Deconnexion</a></li>
                                          </ul>
                                        </li>
                                      </ul>
                                   </div>
                               </div>
                           </div>
                       </nav>`
});