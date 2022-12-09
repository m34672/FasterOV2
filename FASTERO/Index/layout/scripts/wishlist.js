// 串接後端顯示到頁面
const baseUrl = "http://localhost:8080/FasteroV2/wishlist";
const userId = 2;
const types = document.querySelectorAll(".type");
let wishlistData = [];
// 模仿列舉
const wishlistType = [
  "台灣料理",
  "美式餐廳",
  "日式料理",
  "義式料理",
  "法式料理",
  "中式美食",
  "韓式料理",
];

let myStore = document.querySelector("#myStore");

// for all types
types.forEach((e, index) => {
  // 給予每個標籤事件
  e.onclick = () => {
    // 當點撃時重新插入頁面
    addFirst(wishlistData.filter((e) => e.storeType === wishlistType[index]));
  };
});

// 連結到商家
// const storelinks = document.querySelectorAll(".storelinks");
// storelinks.forEach((e, index) => {
//   console.log(e);
//   e.onclick = () => {
// storelinks.href = ".../Index/storesheet.html";
//   };
// });

// 進入時執行
async function init() {
  try {
    // ajax get wishlist
    await fetch(`${baseUrl}?userId=${userId}`)
      .then((res) => res.json())
      .then((data) => {
        // 賦值給全域變數
        wishlistData = data;
        addFirst(data);
      });
  } finally {
    document.querySelector(".loading-box").style = "display:none";
  }
}

function addFirst(data) {
  myStore.innerHTML = data
    .map((e, i) =>
      Template(
        e.storePreviewImgBase64,
        e.storeName,
        e.storeType,
        e.storeIntroduction
      )
    )
    .join("");
  document.querySelectorAll(".one_third").forEach((e, i) => {
    if (i % 3 === 0) e.className = "one_third first";
  });
}

function Template(base64, storeName, storeType, content) {
  return `<article class="one_third">
           <a href="storesheet.html" class="storelinks"><img class="btmspace-30" src="${base64}" alt="" /></a>
           <h4 class="nospace btmspace-10 font-x1 uppercase">
             ${storeName}
           </h4>
           <ul class="nospace btmspace-10 group font-xs">
             <li class="fl_left">
             <li>${storeType}</li>
             </li>
           </ul>
           <hr />
           <p>
             ${content}
           </p>
           <p class="nospace"><a href="#">Read More &raquo;</a></p>
         </article>`;
}

window.addEventListener("load", init);
