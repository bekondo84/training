var formHeader = Vue.component("f-header", {
     props : ["data", "meta", "menu"],
     data() {
        return {
          i18n: {}
        }
     }, computed : {
         title() { return this.meta != null ? this.meta.formTitle : ""; },
         creatable() {
             if (this.meta != null && this.data != null && this.data.pk ==null && !this.meta.creatable) {
                return false;
             } else if (this.meta != null && this.data != null && this.data.pk >0 && !this.meta.updatable) {
                return false ;
             }else {
                return this.menu!= null && this.menu.canWrite ;
             }
         },
         deletable() {
            if (this.meta== null || !this.meta.deletable || this.data==null || this.data.pk==null){
                return false;
            } else {
               return this.menu!= null && this.menu.canDelete ;
            }
         },
         actions() {
             return this.meta !=null && this.menu.actions != null ? this.menu.actions.filter(act => act.type=="view" && act.active) : [] ;
        },
        hideCancelBtn() {
             return this.menu
        }
     }, methods : {
         async remove() {
            var answer = confirm(this.getMessage('delete.alert'));
            if (answer) {
                try {
                  await axios.delete(this.menu.source.concat("/").concat(this.data.pk));
                  this.$emit("refresh-list-form");
                } catch (error) {
                   this.notifyError(error) ;
                }
            }
         },async save() {
           try {
              let response = await axios.post(this.menu.source, this.data);
              this.$emit("refresh-list-form") ;
            }catch (error) {
               this.notifyError(error) ;
            }
         },cancel() {
            this.$emit("cancel-event");
         },processAction(action) {
            var copy = Object.assign({}, action);
            this.$emit("process-action", copy);
        }, getMessage(key) {
            return this.i18n!= null && this.i18n[key]!=null ? this.i18n[key]: key;
        }
     }, async created() {
        try {
             let response = await axios.get("/api/v1/i18n?keys=save.btn,delete.btn,other.btn,cancel.btn,delete.alert");
             this.i18n = response.data;
         } catch(error) {
            this.notifyError(error);
         }
     },template: ` <div class="title-bloc">
                 <div class="title-bar">
                     <div  class="title">{{title}}</div>
                     <div class="input-group margin-left-auto width-350"></div>
                </div>
                  <div class="title-bar">
                     <ul class="nav">
                       <li class="nav-item" v-if="creatable">
                         <a class="nav-link link-primary" aria-current="page" href="#" @click="save()">{{getMessage('save.btn')}}</a>
                       </li>
                       <li class="nav-item" v-if="deletable">
                        <a class="nav-link link-danger" aria-current="page" href="#" @click="remove()">{{getMessage('delete.btn')}}</a>
                       </li>
                       <li class="nav-item dropdown"  v-if="actions.length > 0">
                           <a class="nav-link link-secondary dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">{{getMessage('other.btn')}}</a>
                           <ul class="dropdown-menu">
                             <li v-for="action in actions">
                                <a class="dropdown-item" href="#" @click="processAction(action)">{{action.label}}</a>
                             </li>
                           </ul>
                         </li>
                         <li class="nav-item">
                            <a class="nav-link link-secondary" aria-current="page" href="#" @click="cancel()">{{getMessage('cancel.btn')}}</a>
                         </li>
                     </ul>
                     <div class="margin-left-auto"></div>
                  </div>
                </div>`
});