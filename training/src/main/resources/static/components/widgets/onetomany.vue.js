var onetomany = Vue.component("v-onetomany", {
     props: ["field", "data", "disabled"],
     data() {
        return {
           meta : null,
           instance : null,
           dialog : null,
           keyValue: 12,
           type : "view",
           selectedItem : null
        }
     },computed : {
        columns() { return this.meta != null ? this.meta.columns : []},
        datas() {  return this.data == null ? [] : this.data[this.field.name]},
        id() { return "d-".concat(this.field.name) ;},
        editable() {
           if (this.disabled) {
              return false;
           }
           return  this.field.editable
        } ,
        deletable() {
           if (this.disabled) {
             return false;
           }
           return this.field.deletable
        },
        updatable() {
           if (this.disabled) {
            return false;
           }
           return this.field.updatable;
        },
        key() { return new Date().getTime();}
     },methods : {
        fieldValue(item, col) {
          if (item == null ) return null;
          if (item[col.name] != null && typeof item[col.name] == 'object') {
             return item[col.name] != null ? item[col.name].value : item[col.name] ;
          }
          return item[col.name];
        }, async add() {
             try {
                this.type = "view";
                let response = await axios.get("/api/v1/instance/".concat(this.field.metadata));
                this.instance = response.data ;
                this.showDialog();
             } catch (error) {
                 console.log(error);
              }
         },showDialog() {
            try {
                this.dialog = "t-dialog";
                this.keyValue = new Date().getTime();
                var modal = new bootstrap.Modal(document.getElementById(this.id), {
                  keyboard: false
                });
                modal.show();
            } catch(error) {}
         },itemSelected(item) {
            this.selectedItem = item;
            this.instance = Object.assign({}, item) ;
         }, isSelected(item) {
             return item == this.selectedItem ;
         },update() {
            this.type = "view";
            if (this.instance != null) {
              this.showDialog();
            } else {
               alert("Veuillez selectionner une ligne");
            }
         },view() {
             this.type = "view-only";
             if (this.instance != null) {
               this.showDialog();
             } else {
                alert("Veuillez selectionner une ligne");
             }
         },refreshList(item) {
              if (this.data[this.field.name] != null) {
                 if (item.pk == null) {
                    this.data[this.field.name].push(item);
                 } else {
                      const index = this.data[this.field.name].findIndex((val) => val.pk==item.pk);
                      this.data[this.field.name].splice(index, 1, item);
                 }
              }
          }
     },async created() {
       try {
         let response = await axios.get("/api/v1/meta/".concat(this.field.metadata));
         this.meta = response.data;
       }catch (error) {
           console.log(error);
       }
    },template : `<div>
                        <div>
                           <nav class="nav">
                             <a class="nav-link data-button" aria-current="page" href="#"
                                 @click="add()" v-if="editable">
                                <img src="../../images/add.gif" class="rounded">
                             </a>
                             <a class="nav-link data-button" href="#"  @click="update()" v-if="updatable">
                                 <img src="../../images/upda.gif" class="rounded">
                             </a>
                             <a class="nav-link data-button" href="#" @click="view()">
                                <img src="../../images/view.gif" class="rounded">
                             </a>
                             <a class="nav-link data-button" href="#" v-if="deletable">
                                <img src="../../images/dele.gif" class="rounded">
                             </a>
                           </nav>
                           <component v-bind:is="dialog"
                               :field="field"
                               :meta="meta"
                               :data="instance"
                               :type="type"
                               :key="key"
                               @dialog-save="refreshList"></component>
                        </div>
                        <div class="table-responsive">
                                 <table class="table table-striped table-hover table-sm">
                                     <thead class="table-header-theme">
                                     <tr class="table-header">
                                         <th scope="col" v-for="c of columns">{{c.label}}</th>
                                     </tr>
                                     </thead>
                                     <tbody>
                                         <tr class="clickable-row" v-for="data of datas"  :class="{rowSelected : isSelected(data)}">
                                             <td v-for="col of columns" v-on:click="itemSelected(data)">
                                                 <span  class="form-check form-switch" v-if="col.type == 'checkbox'">
                                                     <input class="form-check-input" type="checkbox" v-model="data[col.name]" :checked="data[col.name]">
                                                 </span>
                                                 <span v-else>{{fieldValue(data, col)}}</span>
                                             </td>
                                         </tr>
                                     </tbody>
                                 </table>
                             </div>
                      </div>`
});