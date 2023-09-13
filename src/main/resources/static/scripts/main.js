document.addEventListener("DOMContentLoaded", () => {
  const swiperWrapper = document.querySelector(".swiper-wrapper");
  const swiperDots = document.querySelector(".swiper-dots");
  let currentIndex = 0;

  function slideTo(index) {
    currentIndex = index;
    const translateXValue = -currentIndex * 100;
    swiperWrapper.style.transform = `translateX(${translateXValue}%)`;
    const dots = document.querySelectorAll(".swiper-dot");
    dots.forEach((dot, i) => {
      dot.classList.toggle("active", i === currentIndex);
    });
  }

  function handleDotClick(index) {
    slideTo(index);
  }

  for (let i = 0; i < 4; i++) {
    const dot = document.createElement("div");
    dot.classList.add("swiper-dot");
    dot.addEventListener("click", () => handleDotClick(i));
    swiperDots.appendChild(dot);
  }

  let autoplayInterval;

  function startAutoplay() {
    autoplayInterval = setInterval(() => {
      if (currentIndex < 3) {
        slideTo(currentIndex + 1);
      } else {
        slideTo(0);
      }
    }, 8000);
  }

  slideTo(currentIndex);
  startAutoplay();

  const showButton = document.getElementById("showButton");
  const productCards = document.querySelectorAll(".product_card");
  const initialVisibleCount = 4;

  // Show the initial set of product cards
  for (let i = 0; i < initialVisibleCount; i++) {
    productCards[i].style.display = "block";
  }

  // Add event listener to the "Show More" button
  showButton.addEventListener("click", () => {
    // Show all product cards
    for (const card of productCards) {
      card.style.display = "block";
    }

    // Hide the "Show More" button after revealing all cards
    showButton.style.display = "none";
  });

  const sliderTrack = document.querySelector(".slider-track");
  const sliderItems = document.querySelectorAll(".slider-item");
  const sliderContainer = document.querySelector(".slider-container");

  function updateSliderPosition() {
    sliderTrack.style.transform = `translateX(${-currentIndex * 300}px)`;
  }

  function nextSlide() {
    currentIndex = (currentIndex + 1) % sliderItems.length;
    updateSliderPosition();
  }

  function prevSlide() {
    currentIndex = (currentIndex - 1 + sliderItems.length) % sliderItems.length;
    updateSliderPosition();
  }

  // Click event handlers
  sliderContainer.addEventListener("click", (event) => {
    const clickX = event.clientX;
    const containerRect = sliderContainer.getBoundingClientRect();
    if (clickX < containerRect.left + containerRect.width / 2) {
      prevSlide();
    } else {
      nextSlide();
    }
  });

  // Touch event handlers
  let touchStartX = 0;
  sliderContainer.addEventListener("touchstart", (event) => {
    touchStartX = event.touches[0].clientX;
  });

  sliderContainer.addEventListener("touchend", (event) => {
    const touchEndX = event.changedTouches[0].clientX;
    const deltaX = touchEndX - touchStartX;
    if (deltaX > 0) {
      prevSlide();
    } else {
      nextSlide();
    }
  });

  // Initial setup
  updateSliderPosition();
});

document.onreadystatechange = function () {
  if (document.readyState !== "complete") {
    document.querySelector("body").style.visibility = "hidden";
    document.querySelector("#loader").style.visibility = "visible";
  } else {
    document.querySelector("#loader").style.display = "none";
    document.querySelector("body").style.visibility = "visible";
  }
};

let nums = document.querySelectorAll(".nums .num");
let section = document.querySelector(".statistics");
let started = false;

window.onscroll = function () {
  // Calculate the middle of the screen
  const middleOfScreen = window.innerHeight / 2;
  
  // Calculate the position of the section relative to the middle of the screen
  const sectionPosition = section.getBoundingClientRect().top + window.screenY - middleOfScreen;

  if (!started && sectionPosition <= 0) {
    nums.forEach((num) => startCount(num));
    started = true;
  }
};

function startCount(el) {
  let goal = el.dataset.goal;
  let count = setInterval(() => {
    el.textContent++;
    if (el.textContent == goal) {
      clearInterval(count);
    }
  }, 1);
}


// startCount))
const navOpenBtn = document.querySelector(".nav-hamburger");
const navCloseBtn = document.querySelector(".nav-close");
const nav = document.querySelector(".navbar");
navOpenBtn.addEventListener("click", () => {
  nav.classList.add("openNav");
  nav.classList.remove("openSearch");
});
navCloseBtn.addEventListener("click", () => {
  nav.classList.remove("openNav");
});
