var manytomany = Vue.component("v-manytomany", {
     props: ["field", "data", "desabled"],
     data() {
        return {
           meta : null,
           dialog : "t-dialog",
           selectItem : null
        }
     },computed : {
        columns() { return this.meta != null ? this.meta.columns : []},
        id() { return this.field != null ?  "#d-".concat(this.field.name) : new Date().getTime();},
        editable() { return !this.desabled && this.field.editable} ,
        deletable() { return !this.desabled && this.field.deletable }
     },methods : {
        fieldValue(item, col) {
            if (item == null) return item;
             if (typeof item[col.name] == 'object' && item[col.name] != null) {
                 return item[col.name].value ;
             }
             return item[col.name];
        }, itemSelected(item) {
              this.selectItem = item ;
        }, remove() {
            if (this.selectItem != null) {
               const index =  this.data[this.field.name].indexOf(this.selectItem);

               if (index > -1) {
                   this.data[this.field.name].splice(index, 1);
               }
            }
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
                                data-bs-toggle="modal" :data-bs-target="id" v-if="editable">
                                <img src="../../images/add.gif" class="rounded">
                             </a>
                             <a class="nav-link data-button" href="#">
                                 <img src="../../images/view.gif" class="rounded">
                             </a>
                             <a class="nav-link data-button" href="#" @click="remove()" v-if="deletable">
                                <img src="../../images/dele.gif" class="rounded">
                             </a>
                           </nav>
                           <component v-bind:is="dialog"
                                      :field="field"
                                      :meta="meta"
                                      :data="data"
                                      type="list"></component>
                        </div>
                        <div class="table-responsive">
                                 <table class="table table-striped table-hover table-sm">
                                     <thead>
                                     <tr>
                                         <th scope="col" v-for="c of columns">{{c.label}}</th>
                                     </tr>
                                     </thead>
                                     <tbody>
                                         <tr class="clickable-row" v-for="row of data[field.name]">
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