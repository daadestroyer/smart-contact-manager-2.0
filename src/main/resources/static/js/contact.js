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
        document.querySelector("#contact_image").src = data.cloudinaryImagePublicId;
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


function deleteContact(id) {
    Swal.fire({
        title: "Do you want to delete the user?",
        showCancelButton: true,
        confirmButtonText: "Delete",
        cancelButtonText: "Cancel",
        confirmButtonColor: "#d33",  // Red color for "Delete"
        cancelButtonColor: "#6c757d" // Gray color for "Cancel"
    }).then((result) => {
        if (result.isConfirmed) {
            // ✅ Show confirmation alert first
            Swal.fire({
                title: "Deleted!",
                text: "User has been removed.",
                icon: "success",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "OK"
            }).then(() => {
                // ✅ Redirect only AFTER user clicks "OK"
                const url = "http://localhost:8081/user/contact/deleteContact/" + id;
                window.location.replace(url);
            });
        } else if (result.dismiss === Swal.DismissReason.cancel) {
            // ✅ If "Cancel" is clicked
            Swal.fire({
                title: "Cancelled",
                text: "User is safe.",
                icon: "info",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "OK"
            });
        }
    });
}