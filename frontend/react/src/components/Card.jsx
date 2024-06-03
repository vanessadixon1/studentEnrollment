import {
    Heading,
    Avatar,
    Box,
    Center,
    Image,
    Flex,
    Text,
    Stack,
    useColorModeValue,
    Button,
    useDisclosure,
    AlertDialogOverlay,
    AlertDialogContent,
    AlertDialogHeader,
    AlertDialogBody,
    AlertDialogFooter,
    AlertDialog
} from '@chakra-ui/react';
import { useRef} from "react";
import {deleteStudent} from "../services/client.js";

import {errorNotification, successNotification} from "../services/Notification.js";
import PopoverForm from "./PopoverForm.jsx";


export default function CardWithImage({id,name, email, age, gender, phoneNumber, fetchStudent}) {
    const { isOpen, onOpen, onClose } = useDisclosure()
    const cancelRef = useRef()
    gender = gender.toLowerCase() === 'MALE'.toLowerCase() ? 'men' : 'women';
    const randomPictureId = Math.floor(Math.random() * 85)

    function deletingStudent() {
        deleteStudent({id}).then(res => {
            successNotification("Student Deleted", `${name} successfully deleted`)
        }).catch(err => {
            errorNotification(err.code, err.response.data.message)
        }).finally(() => {
            onClose()
            fetchStudent()
        })
    }

    return (
        <Center py={6}>
            <Box
                maxW={'300px'}
                 minW={'300px'}
                w={'full'}
                m={2}
                bg={useColorModeValue('white', 'gray.800')}
                boxShadow={'lg'}
                rounded={'md'}
                overflow={'hidden'}>
                <Image
                    h={'120px'}
                    w={'full'}
                    src={
                        'https://images.unsplash.com/photo-1612865547334-09cb8cb455da?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80'
                    }
                    objectFit={'cover'}
                />
                <Flex justify={'center'} mt={-12}>
                    <Avatar
                        size={'xl'}
                        src={
                            `https://randomuser.me/api/portraits/${gender}/${randomPictureId}.jpg`
                        }
                        alt={'Author'}
                        css={{
                            border: '2px solid white',
                        }}
                    />
                </Flex>

                <Box p={6}>
                    <Stack spacing={2} align={'center'} mb={5}>
                        {/*<Tag borderRadius={"full"}>{id}</Tag>*/}
                        <Heading fontSize={'2xl'} fontWeight={500} fontFamily={'body'}>
                            {/*{name}*/}
                            <PopoverForm fetchStudents={fetchStudent} id={id} name={name} email={email} phoneNumber={phoneNumber}/>
                        </Heading>
                        <Text color={'gray.500'}>{email}</Text>
                        <Text color={'gray.500'}>Age {age}</Text>
                        <Button bg={'red.400'} color={'white'} rounded={'full'} onClick={onOpen}>Delete</Button>

                        <AlertDialog
                            isOpen={isOpen}
                            leastDestructiveRef={cancelRef}
                            onClose={onClose}
                        >
                            <AlertDialogOverlay>
                                <AlertDialogContent>
                                    <AlertDialogHeader fontSize='lg' fontWeight='bold'>
                                        Delete Student
                                    </AlertDialogHeader>

                                    <AlertDialogBody>
                                        Are you sure? You can't undo this action afterwards.
                                    </AlertDialogBody>

                                    <AlertDialogFooter>
                                        <Button ref={cancelRef} onClick={onClose}>
                                            Cancel
                                        </Button>
                                        <Button colorScheme='red' onClick={() => deletingStudent()} ml={3}>
                                            Delete
                                        </Button>
                                    </AlertDialogFooter>
                                </AlertDialogContent>
                            </AlertDialogOverlay>
                        </AlertDialog>
                    </Stack>
                </Box>
            </Box>
        </Center>
    );
}