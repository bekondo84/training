var l_header = Vue.component("l-header", {
     props: ["menu"],
     data() {
         return {
              actions : this.menu.actions
         }
     },computed :{
         haveActions() {
             return this.actions != null ? this.actions.length : 0;
         }
     },template : `<div class="toolbar">
                      <!--Bar de titre -->
                      <div class="d-flex flex-row justify-content-around" >
                          <div> Titre formulaire</div>
                          <div>
                              <form class="d-flex">
                                  <input class="form-control me-1" type="search" placeholder="Search" aria-label="Search">
                                  <button class="btn btn-primary" type="submit">Search</button>
                              </form>
                          </div>
                      </div>
                      <!--Bar d'outil  -->
                      <div class="actionsbar" >
                          <ul class="nav nav-pills" v-if="haveActions">
                              <li class="nav-item dropdown">
                                  <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Actions :</a>
                                  <ul class="dropdown-menu">
                                      <li  v-for="action of actions">
                                          <a class="dropdown-item"  href="#">{{action.name}}</a>
                                      </li>
                                  </ul>
                              </li>
                          </ul>
                          <nav aria-label="Page navigation example">
                              <ul class="pagination">
                                  <li class="page-item">
                                      <a class="page-link" href="#" aria-label="Previous">
                                          <span aria-hidden="true">&laquo;</span>
                                      </a>
                                  </li>
                                  <li class="page-item"><a class="page-link" href="#">1</a></li>
                                  <li class="page-item"><a class="page-link" href="#">2</a></li>
                                  <li class="page-item"><a class="page-link" href="#">3</a></li>
                                  <li class="page-item">
                                      <a class="page-link" href="#" aria-label="Next">
                                          <span aria-hidden="true">&raquo;</span>
                                      </a>
                                  </li>
                              </ul>
                          </nav>
                      </div>
                  </div>`
});