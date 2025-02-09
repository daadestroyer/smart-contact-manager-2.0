console.log("hello baby")

const viewContactModal = document.getElementById("view_contact_modal");

const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
    id: 'modalEl',
    override: true
};

const contactModal = new Modal(viewContactModal, options);

async function loadContactData(contactId) {
    try {
        
        console.log(contactId);

        const response = await fetch(`http://localhost:8081/contact/contact-details/${contactId}`);
        const data = await response.json();
        console.log(data);
        document.querySelector("#contact_name").innerHTML = data.contactName;
        document.querySelector("#contact_email").innerHTML = data.contactEmail;
        document.querySelector("#contact_id").innerHTML = data.contactId;
        document.querySelector("#contact_desc").innerHTML = data.contactDescription;
        document.querySelector("#contact_addr").innerHTML = data.contactAddress;
        document.querySelector("#contact_phone").innerHTML = data.contactPhoneNumber;
        document.querySelector("#contact_date").innerHTML = data.createdDate.split("T")[0];
        document.querySelector("#contact_fb").innerHTML = data.facebookLink
        document.querySelector("#contact_website").innerHTML = data.websiteLink
        document.querySelector("#contact_fav").innerHTML = data.contactFavourite
        openContactModal();
        
    } catch (error) {
        console.error("Error fetching contact data:", error);
    }
    
}
function openContactModal(){
    contactModal.show();
}
function closeContactModal() {
    contactModal.hide();
}

