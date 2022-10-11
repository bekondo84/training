var l_header = Vue.component("l-header", {
     props: ["menu", "meta", "backbtn"],
     data() {
         return {
            selectItem : null,
            i18n: {}
         }
     },methods: {
        async  createAction() {
           try {
                let response = await axios.get("/api/v1/instance/".concat(this.menu.metadata));
                this.$emit("created-action", response.data);
           }catch (error) {
              this.$emit("notify-error", error) ;
           }
         },cancel() {
           this.$emit("cancel-event");
         },processAction(action) {
            this.$emit("process-action", action);
         }, searchAction(text) {
             this.$emit("search-action", text);
         },getMessage(key) {
            return this.i18n!= null && this.i18n[key]!=null ? this.i18n[key]: key;
         }
     },computed :{
         creatable() {
             if (this.meta != null && !this.meta.creatable) {
                return false;
             } else {
                  return  this.menu != null ? this.menu.canCreate : false ;
             }
         },
         actions() {
             return this.menu.actions != null ? this.menu.actions.filter(act => act.type=="list" && act.active) : [];
         },title() { return this.meta != null ? this.meta.listTitle : ""; },
     }, async created() {
        try {
             let response = await axios.get("/api/v1/i18n?keys=cancel.btn,create.btn,other.btn");
             this.i18n = response.data;
         } catch(error) {
            this.notifyError(error);
         }
     },template : `<div class="title-bloc">
      <div class="title-bar">
          <div class="title"><p>{{title}}</p></div>
          <v-search @search-action="searchAction"></v-search>
     </div>
       <div class="title-bar">
          <ul class="nav">
            <li class="nav-item nav-item-button-margin-rigth" v-if="backbtn">
              <a class="btn btn-secondary btn-sm" aria-current="page" href="#" @click="cancel()">{{getMessage('cancel.btn')}}</a>
            </li>
            <li class="nav-item  nav-item-button-margin-rigth" v-if="creatable">
              <a class="btn btn-danger btn-sm" aria-current="page" href="#" @click="createAction()">{{getMessage('create.btn')}}</a>
            </li>
            <li class="nav-item  nav-item-button-margin-rigth dropdown" v-if="actions.length > 0">
                <a class="nav-link link-secondary dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">{{getMessage('other.btn')}}</a>
                <ul class="dropdown-menu">
                  <li v-for="action in actions">
                     <a class="dropdown-item" href="#"  @click="processAction(action)">{{action.label}}</a>
                  </li>
                </ul>
              </li>
          </ul>
          <div class="margin-left-auto"><v-pagination></v-pagination></div>
       </div>
     </div>`

});