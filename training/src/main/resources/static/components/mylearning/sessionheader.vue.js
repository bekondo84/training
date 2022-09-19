var mylearningheader = Vue.component("s-header", {
   props: ["menu", "meta", "backbtn"],
   data() {
      return {
      }
   }, computed : {
       title() { return this.meta != null ? this.meta.listTitle : "" }
   }, template: `<div>
                       <div class="title-bar">
                           <div class="title"><p>{{title}}</p></div>
                           <div class="input-group margin-left-auto width-350">
                             <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                             <button type="button" class="btn btn-outline-primary">search</button>
                           </div>
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
                           <div class="margin-left-auto">Pagination Block</div>
                        </div>
                  </div>`
});