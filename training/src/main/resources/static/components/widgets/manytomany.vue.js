var manytomany = Vue.component("v-manytomany", {
     props: ["field", "data", "desabled"],
     data() {
        return {
           meta : null,
           dialog : "t-dialog",
           selectItem : null,
           type : "list"
        }
     },computed : {
        columns() { return this.meta != null ? this.meta.columns : []},
        id() { return "d-".concat(this.field.name) ;},
        editable() { return !this.desabled && this.field.editable} ,
        deletable() { return !this.desabled && this.field.deletable },
        key() { return new Date().getTime();}
     },methods : {
        fieldValue(item, col) {
            if (item == null) return item;
             if (typeof item[col.name] == 'object' && item[col.name] != null) {
                 return item[col.name].value ;
             }
             return item[col.name];
        }, itemSelected(item) {
              this.selectItem = item ;
        }, isSelected(item) {
           return item == this.selectItem ;
        }, remove() {
            if (this.selectItem != null) {
               const index =  this.data[this.field.name].indexOf(this.selectItem);

               if (index > -1) {
                   this.data[this.field.name].splice(index, 1);
               }
            } else {
                alert("Veuillez selectionner une ligne");
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
      },view() {
            this.type = "view-only";
            if (this.instance != null) {

              this.showDialog();
            } else {
               alert("Veuillez selectionner une ligne");
            }
        }, add() {
            this.type = "list";
            this.showDialog();
        }
     },async created() {
       try {
         if (!this.data[this.field.name]) {
            this.data[this.field.name] = [];
         }
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
                             <a class="nav-link data-button" href="#" @click="remove()" v-if="deletable">
                                <img src="../../images/dele.gif" class="rounded">
                             </a>
                           </nav>
                           <component v-bind:is="dialog"
                                      :field="field"
                                      :meta="meta"
                                      :data="data"
                                      :type="type"></component>
                        </div>
                        <div class="table-responsive">
                                 <table class="table table-striped table-hover table-sm">
                                     <thead class="table-header-theme">
                                     <tr  class="table-header">
                                         <th scope="col" v-for="c of columns">{{c.label}}</th>
                                     </tr>
                                     </thead>
                                     <tbody>
                                         <tr class="clickable-row" v-for="row of data[field.name]"  :class="{rowSelected : isSelected(row)}">
                                             <td v-for="col of columns" @click="itemSelected(row)">
                                                 <span  class="form-check form-switch" v-if="col.type == 'checkbox'">
                                                    <input class="form-check-input" type="checkbox" v-model="row[col.name]" :checked="row[col.name]">
                                                 </span>
                                                 <span v-else>{{fieldValue(row, col)}}</span>
                                             </td>
                                         </tr>
                                     </tbody>
                                 </table>
                             </div>
                      </div>`
});