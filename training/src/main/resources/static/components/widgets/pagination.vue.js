var pagination = Vue.component("v-pagination", {
  props : ["page"],
  data() {
     return {

     }
  }, methods : {
        next() {
          if (this.page.page < this.page.size) {
              this.page.page = this.page.page + 1 ;
              this.$emit("pagination-action");
          }
        }, preview() {
           if (this.page.page > 1) {
                this.page.page = this.page.page - 1 ;
                this.$emit("pagination-action");
           }
        }
  }, computed : {
       hidden() { return this.page.size > 1 ;}
  }, template : `<nav aria-label="Page navigation example" v-if="hidden">
                   <ul class="pagination pagination-sm">
                     <li class="page-item">
                       <a class="page-link" href="#" aria-label="Previous" @click="preview">
                         <img aria-hidden="true" src="../../images/left.png" width="17px" class="rounded">
                       </a>
                     </li>
                     <li>
                        <input aria-hidden="true" class="pag-input" v-model="page.page">
                     </li>
                     <li><span aria-hidden="true" style="margin-right: 10px;">/ {{page.size}}</span></li>
                     <li class="page-item">
                       <a class="page-link" href="#" aria-label="Next"  @click="next">
                         <img aria-hidden="true" src="../../images/right.png" width="17px" class="rounded">
                       </a>
                     </li>
                   </ul>
                 </nav>`
});