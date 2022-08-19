var formHeader = Vue.component("f-header", {
     props : ["data", "meta", "menu"],
     data() {
        return {

        }
     }, computed : {
         title() { return this.data.formTitle}
     }, methods : {
         async save() {
           try {
              let response = await axios.post(this.menu.source, this.data);
            }catch (error) {
               console.log(error);
            }
         }
     },template: ` <nav class="navbar navbar-expand-lg navbar-light bg-light">
                      <!-- Container wrapper -->
                      <div class="container-fluid">
                       <!-- Collapsible wrapper -->
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                          <!-- Left links -->
                          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                              <a class="nav-link"  href="#" @click="save()">Enregistrer</a>
                            </li>
                            <li class="nav-item">
                              <a class="nav-link" href="#">Supprimer</a>
                            </li>
                           <li class="nav-item dropdown">
                                 <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                   Dropdown
                                 </a>
                                 <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                   <li><a class="dropdown-item" href="#">Action</a></li>
                                   <li><a class="dropdown-item" href="#">Another action</a></li>
                                   <li><hr class="dropdown-divider"></li>
                                   <li><a class="dropdown-item" href="#">Something else here</a></li>
                                 </ul>
                            </li>
                             <li class="nav-item">
                               <a class="nav-link" href="#">Quitter</a>
                             </li>
                          </ul>
                          <!-- Left links -->
                        </div>
                        <!-- Collapsible wrapper -->
                      </div>
                      <!-- Container wrapper -->
                </nav>`
});