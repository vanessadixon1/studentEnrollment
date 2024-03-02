import {
    Button,
    Drawer,
    DrawerBody,
    DrawerCloseButton,
    DrawerContent, DrawerFooter,
    DrawerHeader,
    DrawerOverlay,
    useDisclosure
} from '@chakra-ui/react';
import CreateStudentForm from "./CreateStudentForm";

const AddIcon =() => "+";
const CloseIcon =() => "x";
const DrawerForm = ({fetchStudents}) => {
    const { isOpen, onOpen, onClose } = useDisclosure()
    return <>
        <Button colorScheme={'teal'} leftIcon={<AddIcon/>} onClick={onOpen}>
            Create customer
        </Button>
        <Drawer isOpen={isOpen} onClose={onClose} size={"xl"}>
            <DrawerOverlay />
            <DrawerContent>
                <DrawerCloseButton />
                <DrawerHeader>Create New Student</DrawerHeader>

                <DrawerBody>
                  <CreateStudentForm fetchStudents={fetchStudents}/>
                </DrawerBody>

                <DrawerFooter>
                    <Button colorScheme={'teal'} leftIcon={<CloseIcon/>} onClick={onClose}>
                        Close
                    </Button>
                </DrawerFooter>
            </DrawerContent>
        </Drawer>
    </>

}

export default DrawerForm