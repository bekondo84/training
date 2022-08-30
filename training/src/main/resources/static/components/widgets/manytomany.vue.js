var manytomany = Vue.component("v-manytomany", {
     props: ["field", "data"],
     data() {
        return {
           meta : null
        }
     },computed : {
        columns() { return this.meta != null ? this.meta.columns : []},
        datas() {  return this.data[this.field.name]}
     },methods : {

     },async created() {
       try {
         let response = await axios.get("/api/v1/meta/".concat(this.field.metadata));
         this.meta = response.data;
       }catch (error) {
           console.log(error);
       }
    },template : `<div style="border: solid 1px green; ">
                        <div>
                           <nav class="nav">
                             <a class="nav-link data-button" aria-current="page" href="#">
                                <img src="../../images/add.gif" class="rounded">
                             </a>
                             <a class="nav-link data-button" href="#">
                                <img src="../../images/dele.gif" class="rounded">
                             </a>
                           </nav>
                        </div>
                        <div class="table-responsive">
                                 <table class="table table-striped table-hover table-sm">
                                     <thead>
                                     <tr>
                                         <th scope="col" v-for="c of columns">{{c.label}}</th>
                                     </tr>
                                     </thead>
                                     <tbody>
                                         <tr class="clickable-row" v-for="data of datas">
                                             <td v-for="col of columns" v-on:dblclick="itemSelected(data)">
                                                 <span v-if="col.type == 'many-to-one' && data[col.name] != null">{{data[col.name].value}}</span>
                                                 <span  class="form-check form-switch" v-if="col.type == 'checkbox'">
                                                    <input class="form-check-input" type="checkbox" v-model="data[col.name]" :checked="data[col.name]">
                                                 </span>
                                                 <span v-else>{{data[col.name]}}</span>
                                             </td>
                                         </tr>
                                     </tbody>
                                 </table>
                             </div>
                      </div>`
});