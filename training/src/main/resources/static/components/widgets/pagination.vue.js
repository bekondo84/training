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
                         <span aria-hidden="true">&laquo;</span>
                       </a>
                     </li>
                     <li class="page-item"><a class="page-link" href="#">1</a></li>
                     <li class="page-item"><a class="page-link" href="#">2</a></li>
                     <li class="page-item"><a class="page-link" href="#">3</a></li>
                     <li class="page-item">
                       <a class="page-link" href="#" aria-label="Next">
                         <span aria-hidden="true">&raquo;</span>
                       </a>
                     </li>
                   </ul>
                 </nav>`
});