const cart = document.querySelector("#cart");
//商品數量加
// var plus = document.getElementById("plus");
// plus.onclick = function () {
//     var input = document.getElementById("number");
//     var number = input.value;
//     // console.log(number);
//     number++;
//     input.value = number;
// }

// //商品數量減
// var minus = document.getElementById("minus");
// minus.onclick = function () {
//     var input = document.getElementById("number");
//     var number = input.value;
//     number--;
//     if (number == -1) {
//         return 0;
//     }
//     input.value = number;
// }

// [TheMinus,TheMinus,TheMinus,TheMinus]
document.querySelectorAll(".TheMinus").forEach((e, i) => {
  e.onclick = () => {
    // [TheNumber,TheNumber,TheNumber,TheNumber]
    let number = document.querySelectorAll(".TheNumber")[i];

    if (number.value == 0) return;
    number.value = number.value - 1;
  };
});

document.querySelectorAll(".ThePlus").forEach((e, i) => {
  e.onclick = () => {
    // [TheNumber,TheNumber,TheNumber,TheNumber]
    let number = document.querySelectorAll(".TheNumber")[i];

    number.value = Number(number.value) + 1;
  };
});

// 加入購物車
// document.querySelector("#addButton").onclick = () => addAllCount();

//購物車數字icon
const localCar = localStorage.getItem("car"); //取得car物件
const init = () => {
  cart.innerHTML = !localCar
    ? ""
    : `<span class=" cart-count">${Number(localCar)}</span>`;
};

// 函數內的值(count)是傳入值
const addCart = (count) => {
  if (!count) return;
  let res = 0;

  res = count + Number(localCar); //頁面點餐數量相加+原本購物車數量
  cart.innerHTML = `<span class=" cart-count">${res}</span>`;
  localStorage.setItem("car", res); // 1. 增加一個car物件
};

const addAllCount = () => {
  let allCount = 0;
  document.querySelectorAll(".TheNumber").forEach((e, i) => {
    allCount += Number(e.value);
  });
  // document.querySelectorAll(".TheNumber").value = 0;
  // 點按加入購物車後頁面歸零？尚未做
  addCart(allCount);
};

//抓到放入購物車資料
const wishList = JSON.parse(localStorage.getItem("wishlist"));

function updateLocalStorage() {
  //store the list back to localStorage
  localStorage.setItem("wishList", JSON.stringify(wishList));
}

//add cart
document.querySelector("#addButton").onclick = (e) => {
  //get input value
  // const input = document.querySelectorAll(".product-name");
  // input.forEach((e, i) => {
  //   // console.log(e.textContent);
  // });

  // let i = 0;
  const product = document.querySelectorAll(".product-name");
  const price = document.querySelectorAll(".price");
  const quantity = document.querySelectorAll(".TheNumber");

  let productData = [];
  product.forEach((e, i) => {
    productData.push({
      id: i + 1,
      name: product[i].textContent,
      price: Number(price[i].textContent) * Number(quantity[i].value),
      quantity: Number(quantity[i].value),
    });
  });

  if (!productData.map((e) => e.quantity).reduce((p, c) => p + c)) {
    return;
  }
  addAllCount();
  localStorage.setItem("productData", JSON.stringify(productData));
  localStorage.setItem("productStore", 1);
};

window.addEventListener("load", init);

// 以上是佩佩新增 ＆ 21排以前刪除

// 評論讀取
// function reviewTemplate(review_id, user_id, store_id, review_point, review_text, review_store_response, review_time) {
//     return `<div class="comment">
//     <li>${review_id}${user_id}${review_point}</li>
//     <li>${review_text}</li>
//     <li>${review_time}</li>
//     <li>店家回覆:${store_id}${review_store_response}</li>
//     </div>`
// }

// window.addEventListener("load", () => {

//     //    一載入頁面即取得所有商家資料
//     fetch("http://localhost:8080/fastero/storesheet")
//         .then((res) => res.json())
//         .then((data) => {
//             console.log(data);
//             document.querySelector("#reviewlist").innerHTML = data.map((e) => reviewTemplate(e.review_id, e.user_id, e.store_id, e.review_point, e.review_text, e.review_store_response, e.review_time)).join('');

//         });

// });

// 店家和商品資料讀取
// function storeTemplate(
//     store_name,
//     store_introduction,
//     store_preview_img,
//     store_business_time,
//     product_id,
//     product_name,
//     product_price,
//     product_introduction,
//     product_image
// ) {
//     return `<div id="storeandproduct" class="storepic">${store_preview_img}</div>
//     <div id="storeandproduct" class="storeintr">
//             <li>
//                 <h1>${store_name}</h1>
//             </li>
//             <li>
//                 ${store_introduction}
//             </li>
//             <li>
//                 ${store_business_time}
//             </li>
//             <li><button>檢舉店家</button></li>
//         </div>
//         <div id="storeandproduct" class="product-and-review">
//             <div class="product">
//                 <h2>本店料理</h2>

//                 <div class="short-store">
//                     <!-- <img style="width: 240px; height: 160px" src="${product_image}" alt=""> -->
//                     <h3>${product_name}</h3>
//                     <p hidden>${product_id}</p>
//                     <span>價格NTD: ${product_price}</span>
//                     <span>${product_introduction}</span>
//                     <div class="countbtns">
//                         <button class="countbtn" id="minus"><i class="fa-solid fa-minus"
//                                 style="margin-left: 3px;"></i></button>
//                         <input type="number" value="0" id="number" min="0" style="width: 50px;">
//                         <button class="countbtn" id="plus"><i class="fa-solid fa-plus"
//                                 style="margin-left: 3px;"></i></button>
//                         <a class="tocart" href="放入購物車連結"><i class="fa-solid fa-cart-plus"></i></a>
//                     </div>
//                 </div>
//             </div>`;
// }
// window.addEventListener("load", () => {
//     //    一載入頁面即取得所有商家資料
//     fetch("http://localhost:8080/fastero/storesheet")
//         .then((res) => res.json())
//         .then((data) => {
//             console.log(data);
//             document.querySelector("#storeandproduct").innerHTML = data
//                 .map((e) =>
//                     reviewTemplate(
//                         e.review_id,
//                         e.user_id,
//                         e.store_id,
//                         e.review_point,
//                         e.review_text,
//                         e.review_store_response,
//                         e.review_time
//                     )
//                 )
//                 .join("");
//         });
// });
