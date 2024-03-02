import {
    Popover,
    PopoverTrigger,
    PopoverContent,

    PopoverArrow,
    PopoverCloseButton,
    IconButton, Box, useDisclosure,
} from '@chakra-ui/react'
import {EditIcon} from '@chakra-ui/icons'
import {Form} from "formik";
import {FocusLock} from "@chakra-ui/react";
import {useRef} from "react";
import EditStudentForm from "./EditStudentForm.jsx";

const PopoverForm = ({fetchStudents, id, name, email, phoneNumber}) => {
    const { onOpen, onClose, isOpen } = useDisclosure()
    const firstFieldRef = useRef(null)

    return (
        <>
            <Box display='inline-block' mr={3}>
                {name}
            </Box>
            <Popover
                isOpen={isOpen}
                initialFocusRef={firstFieldRef}
                onOpen={onOpen}
                onClose={onClose}
                placement='right'
                closeOnBlur={false}
            >
                <PopoverTrigger>
                    <IconButton size='sm' icon={<EditIcon />} aria-label={'Done'}/>
                </PopoverTrigger>
                <PopoverContent p={5}>
                    <FocusLock returnFocus persistentFocus={false}>
                        <PopoverArrow />
                        <PopoverCloseButton />
                        {/*<Form firstFieldRef={firstFieldRef} onCancel={onClose} />*/}
                        <EditStudentForm fetchStudents={fetchStudents} id={id} name={name} email={email} phoneNumber={phoneNumber} closePopOver={onClose}/>
                    </FocusLock>
                </PopoverContent>
            </Popover>
        </>
    )
}

export default PopoverForm;