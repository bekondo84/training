var mylearningheader = Vue.component("s-header", {
   props: ["menu", "meta", "backbtn"],
   data() {
      return {

      }
   }, methods: {
       searchAction(text) {
         this.$emit("search-action", text);
       }
   }, computed : {
       title() { return this.meta != null ? this.meta.listTitle : "" }
   }, template: `<div>
                       <div class="title-bar">
                           <div class="title"><p>{{title}}</p></div>
                           <v-search @search-action="searchAction"></v-search>
                      </div>
                        <div class="title-bar">
                           <ul class="nav">
                             <li class="nav-item nav-item-button-margin-rigth">
                               <a class="btn btn-secondary btn-sm" aria-current="page" href="#">Quitter</a>
                             </li>
                             <li class="nav-item  nav-item-button-margin-rigth">
                               <a class="btn btn-danger btn-sm" aria-current="page" href="#" >Créer</a>
                             </li>
                           </ul>
                           <div class="margin-left-auto"><v-pagination></v-pagination></div>
                        </div>
                  </div>`
});