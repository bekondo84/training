var l_header = Vue.component("l-header", {
     props: ["menu", "meta", "backbtn"],
     data() {
         return {
            selectItem : null
         }
     },methods: {
        async  createAction() {
           try {
                let response = await axios.get("/api/v1/instance/".concat(this.menu.metadata));
                this.$emit("created-action", response.data);
           }catch (error) {
             console.log(error);
           }
         },cancel() {
           this.$emit("cancel-event");
         },processAction(action) {
            this.$emit("process-action", action);
         }
     },computed :{
         creatable() { return this.meta != null ? this.meta.creatable : false ;},
         actions() {
             return this.menu.actions != null ? this.menu.actions.filter(act => act.type=="list") : [];
         },title() { return this.meta != null ? this.meta.listTitle : ""; },
     },template : `<div>
      <div class="title-bar">
          <div class="title"><p>{{title}}</p></div>
          <div class="input-group margin-left-auto width-350">
            <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
            <button type="button" class="btn btn-outline-primary">search</button>
          </div>
     </div>
       <div class="title-bar">
          <ul class="nav">
            <li class="nav-item nav-item-button-margin-rigth" v-if="backbtn">
              <a class="btn btn-secondary btn-sm" aria-current="page" href="#" @click="cancel()">Quitter</a>
            </li>
            <li class="nav-item  nav-item-button-margin-rigth" v-if="creatable">
              <a class="btn btn-danger btn-sm" aria-current="page" href="#" @click="createAction()">Créer</a>
            </li>
            <li class="nav-item  nav-item-button-margin-rigth dropdown" v-if="actions.length > 0">
                <a class="nav-link link-secondary dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Autres:</a>
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