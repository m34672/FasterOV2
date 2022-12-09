//商品數量加
var plus = document.getElementsByClassName("plus");
for(var i = 0; i < plus.length; i++){
    plus[i].index = i;
    plus[i].onclick = function(){
        var flag = this.index;
        var q = document.getElementsByClassName("number")[flag];
        var newvalue = parseInt(q.value) + 1;
        q.setAttribute("value",newvalue);
        // changeSum(flag,newvalue);
    }
};
//商品數量減
var plus = document.getElementsByClassName("minus");
for(var j = 0; j < plus.length; j++){
    plus[j].index = j;
    plus[j].onclick = function(){
        var flag = this.index;
        var q = document.getElementsByClassName("number")[flag];
        if(parseInt(q.value)>1){
            var newvalue = parseInt(q.value) - 1;
        q.setAttribute("value",newvalue);
        // changeSum(flag,newvalue);
               
    }
};






// 評論讀取
function reviewTemplate(review_id, user_id, store_id, review_point, review_text, review_store_response, review_time) {
    return `<div class="comment">
    <li>${review_id}${user_id}${review_point}</li>    
    <li>${review_text}</li>
    <li>${review_time}</li>
    <li>店家回覆:${store_id}${review_store_response}</li>    
    </div>`    
}

window.addEventListener("load", () => {


    //    一載入頁面即取得所有商家資料
    fetch("http://localhost:8080/fastero/storesheet")
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
            document.querySelector("#reviewlist").innerHTML = data.map((e) => reviewTemplate(e.review_id, e.user_id, e.store_id, e.review_point, e.review_text, e.review_store_response, e.review_time)).join('');

        });

});

// 店家和商品資料讀取
function storeTemplate(store_name,store_introduction,store_preview_img,store_business_time,product_id,product_name,product_price,product_introduction,product_image) {
    return `<div id="storeandproduct" class="storepic">${store_preview_img}</div>
    <div id="storeandproduct" class="storeintr">
            <li>
                <h1>${store_name}</h1>
            </li>
            <li>
                ${store_introduction}
            </li>
            <li>
                ${store_business_time}
            </li>
            <li><button>檢舉店家</button></li>
        </div>
        <div id="storeandproduct" class="product-and-review">
            <div class="product">
                <h2>本店料理</h2>

                <div class="short-store">
                    <!-- <img style="width: 240px; height: 160px" src="${product_image}" alt=""> -->
                    <h3>${product_name}</h3>
                    <p hidden>${product_id}</p>
                    <span>價格NTD: ${product_price}</span>
                    <span>${product_introduction}</span>
                    <div class="countbtns">
                        <button class="countbtn" id="minus"><i class="fa-solid fa-minus"
                                style="margin-left: 3px;"></i></button>
                        <input type="number" value="0" id="number" min="0" style="width: 50px;">
                        <button class="countbtn" id="plus"><i class="fa-solid fa-plus"
                                style="margin-left: 3px;"></i></button>
                        <a class="tocart" href="放入購物車連結"><i class="fa-solid fa-cart-plus"></i></a>
                    </div>
                </div>                
            </div>`    
}
window.addEventListener("load", () => {


    //    一載入頁面即取得所有商家資料
    fetch("http://localhost:8080/fastero/storesheet")
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
            document.querySelector("#storeandproduct").innerHTML = data.map((e) => storeTemplate(store_name,store_introduction,store_preview_img,store_business_time,product_id,product_name,product_price,product_introduction,product_image)).join('');

        });

});}