var formHeader = Vue.component("f-header", {
     props : ["data", "meta", "menu"],
     data() {
        return {

        }
     }, computed : {
         title() { return this.meta != null ? this.meta.formTitle : ""; },
         creatable() { return this.meta!= null && this.meta.creatable || this.data !=null && this.data.pk >0 && this.meta!= null && this.meta.updatable ;},
         deletable() { return this.meta!= null && this.meta.deletable && this.data !=null &&  this.data.pk != null ;},
         actions() {
                 return this.meta !=null && this.menu.actions != null ? this.menu.actions.filter(act => act.type=="view") : [] ;
            }
     }, methods : {
         async remove() {
            var answer = confirm("Voulez-vous continuer ?");
            if (answer) {
                try {
                  await axios.delete(this.menu.source.concat("/").concat(this.data.pk));
                  this.$emit("refresh-list-form");
                } catch (error) {
                  console.log(error);
                }
            }
         },async save() {
           try {
              let response = await axios.post(this.menu.source, this.data);
            }catch (error) {
               console.log(error);
            }
         },cancel() {
            this.$emit("cancel-event");
         },processAction(action) {
            var copy = Object.assign({}, action);
            this.$emit("process-action", copy);
        }
     },template: ` <div>
                 <div class="title-bar">
                     <div><p><p>{{title}}</p></p></div>
                     <div class="input-group margin-left-auto width-350"></div>
                </div>
                  <div class="title-bar">
                     <ul class="nav">
                       <li class="nav-item" v-if="creatable">
                         <a class="nav-link link-primary" aria-current="page" href="#" @click="save()">Enregistrer</a>
                       </li>
                       <li class="nav-item" v-if="deletable">
                        <a class="nav-link link-danger" aria-current="page" href="#" @click="remove()">Supprimer</a>
                       </li>
                       <li class="nav-item dropdown"  v-if="actions.length > 0">
                           <a class="nav-link link-secondary dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Autres:</a>
                           <ul class="dropdown-menu">
                             <li v-for="action in actions">
                                <a class="dropdown-item" href="#" @click="processAction(action)">{{action.label}}</a>
                             </li>
                           </ul>
                         </li>
                         <li class="nav-item">
                            <a class="nav-link link-secondary" aria-current="page" href="#" @click="cancel()">Quitter</a>
                         </li>
                     </ul>
                     <div class="margin-left-auto"></div>
                  </div>
                </div>`
});