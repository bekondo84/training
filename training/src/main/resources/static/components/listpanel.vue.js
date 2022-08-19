var listComponent = Vue.component("list-component", {
     props : ["menu", "data", "meta"],
     data() {
        return {
           welcome : "Form list",

        }
     },methods : {
          generateUniqueKey() {
                        return "h"+Date.now().toString();
                   },
       generateUniqueKey2() {
                               return "l"+Date.now().toString();
                          },
      itemSelected(item) {
                              this.$emit("item-selected", {item :item, viewMode:"view"});
                         }
     },template: `<div class="editor">
                     <l-header :menu="menu" :key="generateUniqueKey()"/>
                     <l-table  :meta="meta"  :data="data" :key="generateUniqueKey2()"
                                @item-selected="itemSelected"/>
                  </div>`
});