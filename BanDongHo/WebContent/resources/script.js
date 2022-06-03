const bar = document.getElementById('bar');
const close = document.getElementById('close');
const nav = document.getElementById('idnavbar');
if(bar){
	bar.addEventListener('click', () => {
		nav.classList.add('active');
	})
}

if(close){
	close.addEventListener('click', () => {
		navbar.classList.remove('active');
	})
}
