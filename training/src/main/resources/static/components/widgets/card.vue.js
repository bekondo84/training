var scard = Vue.component("card", {
   props: ["data", "img", "title","body"],
   data() {
      return {

      }
   }, methods : {

   }, computed : {

   }, template : `<div class="card mb-3 card-margin" style="max-width: 540px;">
                  <div class="row g-0">
                    <div class="col-md-4">
                      <img :src="img" class="img-fluid rounded-start" alt="...">
                    </div>
                    <div class="col-md-8">
                      <div class="card-body">
                        <h5 class="card-title">Card title</h5>
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                        <a href="#" class="btn btn-primary">View</a>
                        <a href="#" class="btn btn-primary">Register</a>
                      </div>
                    </div>
                  </div>
                </div>`
});