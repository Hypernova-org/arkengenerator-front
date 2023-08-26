const temp = document.querySelector("#table-temp").content;
const list = document.querySelector(".table");
const jsonUrl =
  "https://raw.githubusercontent.com/odilov-a/json-arken/master/eng/sdec-nekra/application.json";

function renderProducts(array, place) {
  place.innerHTML = null;
  array.forEach((product) => {
    const productTemplate = temp.cloneNode(true);
    const productUrl = productTemplate.querySelector(".url");
    productUrl.href = product.url;
    productUrl.textContent = product.generator_model;
    const productDizel = productTemplate.querySelector(".dizel");
    productDizel.textContent = product.diesel_engine;
    const productSlindr = productTemplate.querySelector(".silidr");
    productSlindr.textContent = product.number_of_cylinder;
    const productGenerator = productTemplate.querySelector(".generator");
    productGenerator.textContent = product.alternator;
    const productPr1 = productTemplate.querySelector(".pr1");
    productPr1.textContent = product.prime_kw;
    const productPr2 = productTemplate.querySelector(".pr2");
    productPr2.textContent = product.prime_kva;
    const productW1 = productTemplate.querySelector(".w1");
    productW1.textContent = product.standby_kw;
    const productW2 = productTemplate.querySelector(".w2");
    productW2.textContent = product.standby_kva;

    place.append(productTemplate);
  });
}

fetch(jsonUrl)
  .then((response) => response.json())
  .then((data) => {
    console.log(data);
    renderProducts(data.sdec_nekra, list)
  })
  .catch((error) => console.error("Error fetching data: " + error));
