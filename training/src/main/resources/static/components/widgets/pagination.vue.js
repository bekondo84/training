var pagination = Vue.component("v-pagination", {
  props : [],
  data() {
     return {

     }
  }, methods : {

  }, computed : {

  }, template : `<nav aria-label="Page navigation example">
                   <ul class="pagination pagination-sm">
                     <li class="page-item">
                       <a class="page-link" href="#" aria-label="Previous">
                         <img aria-hidden="true" src="../../images/left.png" width="17px" class="rounded">
                       </a>
                     </li>
                     <li>
                        <input aria-hidden="true" class="pag-input" value="10">
                     </li>
                     <li><span aria-hidden="true">/200</span></li>
                     <li class="page-item">
                       <a class="page-link" href="#" aria-label="Next">
                         <img aria-hidden="true" src="../../images/right.png" width="17px" class="rounded">
                       </a>
                     </li>
                   </ul>
                 </nav>`
});