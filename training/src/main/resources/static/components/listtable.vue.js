var listtable = Vue.component("l-table", {
      props : ["data", "meta"],
      data() {
         return {
            datas: this.data != null ? this.data : [],
            current : null
         }
      },methods : {
          selectAll() {
              if (this.datas != null) {
                  for (var i=0 ; i < this.datas.length; i++) {
                      this.datas[i].selected = !this.datas[i].selected ;
                  }
              }
          },itemSelected(item, viewMode) {
              this.current = item;
              this.$emit("item-selected", {"item" :item, "viewMode":viewMode});
          }, isSelected(item)  {
              return item==this.current;
          }
      },computed : {
          columns() {
              return this.meta != null && this.meta.columns != null ? this.meta.columns : [];
          }
      },template: ` <div class="table-responsive">
                              <table class="table table-striped table-hover table-sm">
                                  <thead class="table-header-theme">
                                  <tr  class="table-header">
                                      <th scope="col" v-for="c of columns">{{c.label}}</th>
                                  </tr>
                                  </thead>
                                  <tbody>
                                      <tr class="clickable-row" v-for="data of datas"  :class="{rowSelected : isSelected(data)}">
                                          <td v-for="col of columns" v-on:dblclick="itemSelected(data, 'view')"  v-on:click="itemSelected(data, 'list')">
                                              <span v-if="col.type == 'many-to-one' && data[col.name] != null">{{data[col.name].value}}</span>
                                              <span class="form-check form-switch" v-else-if="col.type == 'checkbox'">
                                                <input class="form-check-input" type="checkbox" v-model="data[col.name]" :checked="data[col.name]" disabled>
                                              </span>
                                              <span v-else>{{data[col.name]}}</span>
                                          </td>
                                      </tr>
                                  </tbody>
                              </table>
                          </div>`
});