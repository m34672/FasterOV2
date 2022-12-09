const [img, link] = [
  document.querySelector("#randomImg"),
  document.querySelector("#randomLink"),
];
let TheRandom = -1;

const storeMap = {
  0: "storesheet.html",
  1: "storesheet.html",
  2: "storesheet.html",
  3: "storesheet.html",
  4: "storesheet.html",
  5: "storesheet.html",
};

const userId = 2;

function getRandom(number) {
  let r;
  r = Math.floor(Math.random() * number) + 1;
  while (r === TheRandom) {
    r = Math.floor(Math.random() * number) + 1;
  }
  TheRandom = r;
  return r;
}
const heart = document.querySelector("#heart");

function init() {
  let myRandom = getRandom(6);

  console.log(TheRandom);
  fetch(
    `http://localhost:8080/FasteroV2/wishlist?userId=${userId}&storeId=${TheRandom}`
  )
    .then((res) => res.json())
    .then((data) => {
      console.log(data);
      if (!data.length) return;
      let { status } = data[0];

      switch (status) {
        case 1:
          let style = heart.className;
          heart.className = `${style} add-heart`;
          break;
        case 2:
          // history.go();
          break;
      }
    });
  img.src = `./images/${myRandom}.jpg`;
  link.href = `./${storeMap[myRandom]}`;
}

heart.onclick = () => {
  /* 加最愛 */
  let style = heart.className;
  // .className抓到heart的class內容
  if (style.includes("add-heart")) {
    heart.className = style.replace("add-heart", "");
    onInsert("PUT", userId, TheRandom, 0);
    return;
  }
  heart.className = `${style} add-heart`;
  onInsert("POST", userId, TheRandom, 1);
};

// 串接後端顯示到頁面
const baseUrl = "http://localhost:8080/FasteroV2/wishlist";
function onInsert(method, userId, storeId, status) {
  fetch(baseUrl, {
    method,
    headers: {
      "Content-type": "application/json",
    },
    body: JSON.stringify({ userId, storeId, status }),
  }).then((res) => res.json());
}

/* ＝＝＝黑名單＝＝＝ */
const no = document.querySelector("#no");

no.onclick = () => {
  const style_n = no.className;

  no.className = `${style_n} add-no`;
  onInsert("POST", userId, TheRandom, 2);
  heart.className = heart.className.replace("add-heart", "");

  // history.go();  //只重整圖片的方法？
};

//     // 串接後端顯示到頁面
//     const baseUrl = 'http://localhost:8080/FasteroV2/wishlist'
//     function onInsert(method, userId, storeId, status) {
//       fetch(baseUrl, {
//         method,
//         headers: {
//           "Content-type": "application/json"
//         },
//         body: JSON.stringify({ userId, storeId, status }),
//       }).then((res) => res.json())
//     }

window.addEventListener("load", init);
